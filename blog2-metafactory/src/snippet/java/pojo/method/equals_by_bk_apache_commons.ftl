<#import "/library/pattern_functions.ftl" as function />

<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#--stop if generatedJavaMethod is null-->
<#if !(generatedJavaMethod)??>  <#stop "generatedJavaMethod not found in context. It contains the ast of the created method" ></#if>

<#--stop if comparatorFactory is null-->
<#if !(comparatorFactory)??>  <#stop "comparatorFactory not found in context" ></#if>

<#-- Here we show an example of an equals implementation with use of the Apache Commons library. -->

<#-- Add import for EqualsBuilder to the class imports -->
${metafactory.addImportToClass("org.apache.commons.lang3.builder.EqualsBuilder")}

<#-- Retrieve the ast object of the method created by the code instruction and add some extra text -->
<#assign apicomment = generatedJavaMethod.apiComment />
<#assign apicommentText>
    ${apicomment}
    Fields used as business key:
</#assign>

<#assign modelObjectName = modelObject.name />
<#assign class = function.createPojoClassName(modelObjectName) />
<#assign compareObject = "other${class}" />

<#-- Here starts the actual equals implementation -->
if (this == other) {
    return true;
}

if (!(other instanceof ${class})) {
    return false;
}

final ${class} ${compareObject} = (${class}) other;
return new EqualsBuilder()
    <@generateEqualsForBKAttributes />
    .isEquals();

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." />
${generatedJavaMethod.setApiComment(apicommentText)}

<#--------------------------------------------------------------------------------------------------------------------->

<#macro generateEqualsForBKAttributes>
    <#local key = "businesskey" />
    <#local bkAttributes = modelObject.findAttributesByMetaData("businesskey") />
    <#if bkAttributes?size == 0>
        <#stop "No metadata on attributes was found for modelObject ${modelObject.name}.
        Please add <businesskey> with an integer that indicates the order of processing to an attribute that
        should be used for the implementation of the equals method." />
    </#if>
    <#local comparator = comparatorFactory.createMetaDataComparator("businesskey") />
    <#local sortedBkAttributes = metafactory.sort(bkAttributes, comparator) />
    <#list sortedBkAttributes as attribute>
        <#local attributeName = attribute.name />
        <#local attributeType = attribute.type />
        <#local attributeNameFU = attributeName?cap_first />
        <#if metafactory.getJavaType(attributeType) == "boolean">
            <#local getter = "is${attributeNameFU}" />
        <#else>
            <#local getter = "get${attributeNameFU}" />
        </#if>
        .append(this.${getter}(), ${compareObject}.${getter}())

        <#--Add this attribute to the apicomment -->
        <#local value = attribute.getMetaData(key)?number />
        <#local previousComment = apicommentText />
        <#assign apicommentText = " ${previousComment} ${value}) ${attributeName}" >
    </#list>
</#macro>
