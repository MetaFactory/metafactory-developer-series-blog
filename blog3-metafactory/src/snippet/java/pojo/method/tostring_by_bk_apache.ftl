<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#--stop if generatedJavaMethod is null-->
<#if !(generatedJavaMethod)??>  <#stop "generatedJavaMethod not found in context. It contains the ast of the created method" ></#if>

<#-- Retrieve the ast object of the method created by the code instruction and add some extra text -->
<#assign apicomment = generatedJavaMethod.apiComment />
<#assign apicommentText>
    ${apicomment}
    Fields used as business key:
</#assign>

<#assign modelObjectName = modelObject.name />
${metafactory.addImportToClass("org.apache.commons.lang3.builder.ToStringBuilder")}
return new ToStringBuilder()
<@generateToStringForBKAttributesAndReferences />
.toString();

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." />
${generatedJavaMethod.setApiComment(apicommentText)}

<#--------------------------------------------------------------------------------------------------------------------->

<#macro generateToStringForBKAttributesAndReferences>
    <#local key = "businesskey" />
    <#local bkAttributes = modelObject.findAttributesByMetaData(key) />
    <#local bkReferences = modelObject.findReferencesByMetaData(key) />
    <#if bkAttributes?size == 0 && bkReferences?size == 0>
        <#stop "No metadata on attributes or references was found for modelObject ${modelObject.name}.
        Please add <${key}> with an integer that indicates the order of processing to an attribute and/or reference
        that should be used for the implementation of the toString method." />
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
            <#local getter = "is${nameFU}()" />
        <#else>
            <#local getter = "get${nameFU}()" />
        </#if>
        .append("${name}", ${getter})

        <#--Add this attribute or reference to the apicomment -->
        <#local value = attributeOrReference.getMetaData(key) />
        <#local previousComment = apicommentText />
        <#assign apicommentText = " ${previousComment} ${value}) ${name}" >
    </#list>
</#macro>
