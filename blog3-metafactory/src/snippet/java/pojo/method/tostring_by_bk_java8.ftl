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
return
<@addAttributesToMethod />
"]";

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." />
${generatedJavaMethod.setApiComment(apicommentText)}

<#--------------------------------------------------------------------------------------------------------------------->

<#macro addAttributesToMethod>
    <#local index = 0 />
    <#local key = "businesskey" />
    <#local bkAttributes = modelObject.findAttributesByMetaData(key) />
    <#if bkAttributes?size == 0>
        <#stop "No metadata on attributes was found for modelObject ${modelObjectName}.
        Please add <${key}> with an integer that indicates the order of processing to an attribute that
        should be used for the implementation of the equals method." />
    </#if>
    <#local comparator = comparatorFactory.createMetaDataComparator(key) />
    <#local sortedBkAttributes = metafactory.sort(bkAttributes, comparator) />
    <#list sortedBkAttributes as attribute>
        <#local attributeName = attribute.name />
        <#local attributeNameFU = attributeName?cap_first />
        <#local attributeType = attribute.type />
        <#local javaType = metafactory.getJavaType(attributeType) />
        <#if javaType=="boolean" || javaType=="Boolean">
            <#local getter = "is${attributeNameFU}()" />
        <#else>
            <#local getter = "get${attributeNameFU}()" />
        </#if>
        <#if index == 0>
            "${modelObjectName} [${attributeName}=" + ${getter} +
        <#else>
            ", ${attributeName}=" + ${getter} +
        </#if>
        <#local index = index + 1 />

        <#--Add this attribute to the apicomment -->
        <#local previousComment = apicommentText />
        <#local value = attribute.getMetaData(key)?number />
        <#assign apicommentText = " ${previousComment} ${value}) ${attributeName}" >
    </#list>
</#macro>

