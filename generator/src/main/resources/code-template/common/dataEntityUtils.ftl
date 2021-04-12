<#assign queryFields =[]>
<#assign dictFields=[]>

<#list entity.fields as field>
    <#if field.isPrimaryKey>
        <#assign pk=field>
    </#if>
    <#if field.dataFieldUI.query>
        <#assign queryFields = queryFields+[field]>
    </#if>
    <#if field.dataFieldUI.exportExcel>
        <#assign dictFields = dictFields+[field]>
    </#if>
</#list>
<#assign uncapEntityName=entityName?uncap_first>
