<#import "/library/pattern_functions.ftl" as function />

<#--stop if modelPackage is null-->
<#if !(modelPackage)??>  <#stop "modelPackage not found in context" ></#if>

<#--stop if arg1 is null-->
<#if !(arg1)??>  <#stop "arg1 (model object name) not found in context" ></#if>

<#--stop if arg2 is null-->
<#if !(arg2)??>  <#stop "arg2 (model reference name) not found in context" ></#if>

<#assign modelObjectName = arg1 />
<#assign referenceName = arg2 />

<#assign object = modelPackage.findObjectByName(modelObjectName) />
<#assign reference = object.findReferenceByName(referenceName) />
<#assign metaDataKey = "businesskey" />
${reference.hasMetaData(metaDataKey)?c}


