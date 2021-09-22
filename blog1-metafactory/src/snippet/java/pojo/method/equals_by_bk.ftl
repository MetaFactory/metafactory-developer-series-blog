<#import "/library/pattern_functions.ftl" as function />
<#--stop if modelObject is null-->
<#if !(modelObject)??>  <#stop "modelObject not found in context" ></#if>

<#--stop if generatedJavaMethod is null-->
<#if !(generatedJavaMethod)??>  <#stop "generatedJavaMethod not found in context. It contains the ast of the created method" ></#if>

<#--
    At this moment we choose to use all the attributes as businesskey. In the next blog article we will show howto
    use metadata in the model to decide which fields belong to the businesskey. 
-->

<#assign attributes = modelObject.attributes />
<#assign apicomment = generatedJavaMethod.apiComment >
<#assign apicommentText>
${apicomment}

Fields used as businesskey:
</#assign>
<#assign class = function.createPojoClassName(modelObject.name) />
<#assign compareObject = "other${class}" >

${metafactory.addImportToClass("java.util.Objects")}

if (this == other) {
  return true;
}

if (!(other instanceof ${class})) {
  return false;
}

final ${class} ${compareObject} = (${class}) other;

boolean result;
<#list attributes as attribute>
    <#assign attributeName = attribute.name>
    <#assign attributeType = attribute.type>
    <#assign attributeNameFU = attributeName?cap_first>
    <#if metafactory.getJavaType(attributeType) == "boolean" >
      <#assign getter = "is${attributeNameFU}" />
    <#else> <#--not a primitive attribute -->
      <#assign getter = "get${attributeNameFU}" />
    </#if>
    <#if attribute_index == 0>
      result = Objects.equals(${getter}(), ${compareObject}.${getter}());
    <#else>
      result = result && Objects.equals(${getter}(), ${compareObject}.${getter}());
    </#if>
    <#assign counter = attribute_index + 1 >
    <#--Add this field to the apicomment -->
    <#assign previousComment = apicommentText >
    <#assign apicommentText = " ${previousComment} ${counter}) ${attributeName}" >

</#list>

<#--Now set the apicomment we created in this template to the ast object of the method -->
<#assign apicommentText = "${apicommentText}." >
${generatedJavaMethod.setApiComment(apicommentText)}

return result;
