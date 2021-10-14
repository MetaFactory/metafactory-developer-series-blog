<#import "/library/pattern_functions.ftl" as function />

<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#--stop if generatedJavaMethod is null-->
<#if !(generatedJavaMethod)??>  <#stop "generatedJavaMethod not found in context. It contains the ast of the created method" ></#if>

<#-- Here we show an example of a hashCode implementation with use of Java 8. -->

<#-- Add import for Objects to the class imports -->
${metafactory.addImportToClass("java.util.Objects")}

<#-- Retrieve the ast object of the method created by the code instruction and add some extra text -->
<#assign attributes = modelObject.attributes />
<#assign apicomment = generatedJavaMethod.apiComment />
<#assign apicommentText>
    ${apicomment}
    Fields used as business key:
</#assign>

<#assign class = function.createPojoClassName(modelObject.name) />

<#-- Here starts the actual hashCode implementation -->
<#compress>
    return Objects.hash(
    <@generateHashCodeForBKAttributesAndReferences />
    );
</#compress>

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." />
${generatedJavaMethod.setApiComment(apicommentText)}

<#--------------------------------------------------------------------------------------------------------------------->

<#macro generateHashCodeForBKAttributesAndReferences>
    <#local key = "businesskey" />
    <#local bkAttributes = modelObject.findAttributesByMetaData(key) />
    <#local bkReferences = modelObject.findReferencesByMetaData(key) />
    <#if bkAttributes?size == 0 && bkReferences?size == 0>
        <#stop "No metadata on attributes or references was found for modelObject ${modelObject.name}.
        Please add <${key}> with an integer that indicates the order of processing to an attribute and/or reference
        that should be used for the implementation of the hashCode method." />
    </#if>
    <#local comparator = comparatorFactory.createMetaDataComparator(key) />
    <#local sortedBkAttributes = metafactory.sort(bkAttributes, comparator) />
    <#local sortedBkReferences = metafactory.sort(bkReferences, comparator) />
    <#local attributesAndReferences = metafactory.createEmptyList() />
    <#if attributesAndReferences.addAll(sortedBkAttributes) ><#-- ignore result of add method --></#if>
    <#if attributesAndReferences.addAll(sortedBkReferences) ><#-- ignore result of add method --></#if>
    <#list attributesAndReferences as attributeOrReference>
        <#local name = attributeOrReference.name />
        <#local type = attributeOrReference.type />
        <#local nameFU = name?cap_first />
        <#local javaType = metafactory.getJavaType(type) />
        <#if javaType == "boolean" || javaType == "Boolean">
            <#local getter = "is${nameFU}" />
        <#else>
            <#local getter = "get${nameFU}" />
        </#if>
        ${getter}()
        <#sep>,</#sep>

        <#--Add this attribute or reference to the apicomment -->
        <#local value = attributeOrReference.getMetaData(key) />
        <#local previousComment = apicommentText />
        <#assign apicommentText = " ${previousComment} ${value}) ${name}" >
    </#list>
</#macro>
