<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#assign modelObjectName = modelObject.name />
return
<@addAttributesToMethod />
"]";

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
    </#list>
</#macro>

