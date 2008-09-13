<#list foreignKeyModelList as foreignKey>
${keyword("alter table")} ${name} ${keyword("add constraint")} ${foreignKey.name} ${keyword("foreign key")} (<#list foreignKey.columnNameList as columnName>${columnName}<#if columnName_has_next>, </#if></#list>) ${keyword("references")} ${foreignKey.referencedTableName} (<#list foreignKey.referencedColumnNameList as columnName>${columnName}<#if columnName_has_next>, </#if></#list>)${delimiter}
</#list>
