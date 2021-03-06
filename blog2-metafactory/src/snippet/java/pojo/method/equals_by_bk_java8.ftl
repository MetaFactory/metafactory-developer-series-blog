<#import "/library/pattern_functions.ftl" as function />

<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#--stop if generatedJavaMethod is null-->
<#if !(generatedJavaMethod)??>  <#stop "generatedJavaMethod not found in context. It contains the ast of the created method" ></#if>

<#-- Here we show an example of an equals implementation with use of Java 8. -->

<#-- Add import for Objects to the class imports -->
${metafactory.addImportToClass("java.util.Objects")}

<#-- Retrieve the ast object of the method created by the code instruction and add some extra text -->
<#assign apicomment = generatedJavaMethod.apiComment />
<#assign apicommentText>
    ${apicomment}
    Fields used as business key:
</#assign>

<#assign class = function.createPojoClassName(modelObject.name) />
<#assign compareObject = "other${class}" />

<#-- Here starts the actual equals implementation -->
if (this == other) {
    return true;
}

if (!(other instanceof ${class})) {
    return false;
}

final ${class} ${compareObject} = (${class}) other;

boolean result;

<@generateEqualsForBKAttributes />

return result;

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." />
${generatedJavaMethod.setApiComment(apicommentText)}

<#--------------------------------------------------------------------------------------------------------------------->

<#macro generateEqualsForBKAttributes>
    <#local key = "businesskey" />
    <#local bkAttributes = modelObject.findAttributesByMetaData(key) />
    <#if bkAttributes?size == 0>
        <#stop "No metadata on attributes was found for modelObject ${modelObject.name}.
        Please add <${key}> with an integer that indicates the order of processing to an attribute that
        should be used for the implementation of the equals method." />
    </#if>
    <#local comparator = comparatorFactory.createMetaDataComparator(key) />
    <#local sortedBkAttributes = metafactory.sort(bkAttributes, comparator) />
    <#list sortedBkAttributes as attribute>
        <#local attributeName = attribute.name />
        <#local attributeType = attribute.type />
        <#local attributeNameFU = attributeName?cap_first />
        <#local javaType = metafactory.getJavaType(attributeType) />
        <#if javaType=="boolean" || javaType=="Boolean">
            <#local getter = "is${attributeNameFU}" />
        <#else>
            <#local getter = "get${attributeNameFU}" />
        </#if>
        <#local value = attribute.getMetaData(key)?number />
        <#if value == 1>
            result = Objects.equals(${getter}(), ${compareObject}.${getter}());
        <#else>
            result = result && Objects.equals(${getter}(), ${compareObject}.${getter}());
        </#if>

    <#--Add this attribute to the apicomment -->
        <#local previousComment = apicommentText />
        <#assign apicommentText = " ${previousComment} ${value}) ${attributeName}" >
    </#list>
</#macro>

