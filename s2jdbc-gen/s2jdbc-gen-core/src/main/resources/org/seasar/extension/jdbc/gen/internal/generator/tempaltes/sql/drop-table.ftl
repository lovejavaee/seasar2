<#list tableDescList as table>
drop table ${table.fullName}${delimiter}
</#list>

drop table ${schemaInfoFullTableName}${delimiter}