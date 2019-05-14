<#import "common_parts/common.ftl" as c>
<#import "common_parts/common_login.ftl" as l>

<@c.page>
<h1>Добавить нового пользователя</h1>
${message?ifExists}
<@l.login "/registration" true/>
</@c.page>
