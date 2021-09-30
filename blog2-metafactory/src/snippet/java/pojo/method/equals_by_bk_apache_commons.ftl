<#import "/library/pattern_functions.ftl" as function />

<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#--stop if generatedJavaMethod is null-->
<#if !(generatedJavaMethod)??>  <#stop "generatedJavaMethod not found in context. It contains the ast of the created method" ></#if>

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

<#-- This is actual equals implementation -->
if (this == other) {
    return true;
}

if (!(other instanceof ${class})) {
    return false;
}

final ${class} ${compareObject} = (${class}) other;
return new EqualsBuilder()
    <@generateEqualsForAllAttributes />
    .isEquals();

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." />
${generatedJavaMethod.setApiComment(apicommentText)}

<#--------------------------------------------------------------------------------------------------------------------->

<#macro generateEqualsForAllAttributes>
    <#local attributes = modelObject.attributes />
    <#list attributes as attribute>
        <#local attributeName = attribute.name />
        <#assign attributeType = attribute.type />
        <#assign attributeNameFU = attributeName?cap_first />
        <#if metafactory.getJavaType(attributeType) == "boolean">
            <#assign getter = "is${attributeNameFU}" />
        <#else> <#--not a primitive attribute -->
            <#assign getter = "get${attributeNameFU}" />
        </#if>
        .append(this.${getter}(), ${compareObject}.${getter}())

        <#--Add this attribute to the apicomment -->
        <#local counter = attribute_index + 1 />
        <#local previousComment = apicommentText />
        <#assign apicommentText = " ${previousComment} ${counter}) ${attributeName}" >
    </#list>
</#macro>
