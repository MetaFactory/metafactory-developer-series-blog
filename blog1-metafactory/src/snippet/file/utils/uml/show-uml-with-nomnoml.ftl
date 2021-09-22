<#--stop if $modelPackage is null-->
<#if !(modelPackage)??>  <#stop "modelPackage not found in context"> </#if>


<#assign allObjects = modelPackage.objects>
<#--Creates the lines to visualize model at http://www.nomnoml.com/-->
<#list allObjects as object>
    <#assign modelObjectName = object.name>
    <#assign modelObjectNameFL = modelObjectName?uncap_first>
    <#assign modelObjectNamePL = object.getMetaData("name.plural", "${modelObjectName}s")>
    <#assign modelObjectNamePLFL = modelObjectNamePL?uncap_first>
    <#assign attributes = object.getAttributes()>
    <#assign references = object.getReferences()>
    <@showObject modelObjectName>
        <#list attributes as attribute>
            <#if attribute_index == 0>|</#if><#t>
            <#assign attributeName = attribute.name>
            <#assign attributeType = attribute.type>
            <#assign attributeNameFU = attributeName?cap_first>
            <#assign javaType = metafactory.getJavaType(attributeType)>
            <@showAttribute attributeName attributeType /><#sep>;<#sep><#t>
        </#list>
    </@showObject>
    <@showInheritance object />
    <#list references as reference>
        <#assign referenceName = reference.name>
        <#assign referenceNameAU = referenceName?upper_case >
        <#assign referenceType = reference.type>
        <#assign multiplicity = reference.multiplicity>
        <#assign referenceObjectElement = modelPackage.findObjectByName(referenceType)>
        <#assign isEnum = referenceObjectElement.getMetaData("enum")>
     <@showReference modelObjectName referenceType multiplicity></@showReference>
    </#list>
</#list>

<#macro showObject name>
[${name}<#nested>
]
</#macro>

<#macro showInheritance modelObject>
    <#if modelObject.hasBaseObject() >
        <#local parentObject = modelObject.extendsFrom />
[${modelObject.name}]-:>[${parentObject}]
    </#if>
</#macro>

<#macro showAttribute name type>
${name}: ${type}<#t>
</#macro>

<#macro showReference from to multiplicity>
[${from}]->${multiplicity}[${to}]
</#macro>
