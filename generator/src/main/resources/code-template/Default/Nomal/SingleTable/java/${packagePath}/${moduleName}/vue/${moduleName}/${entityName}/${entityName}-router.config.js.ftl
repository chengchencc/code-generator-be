<#assign lowerCamalEntityName = entityName?uncap_first>
import { RouteView } from '@/layouts/index'
import { bxAnaalyse } from '@/core/icons'

export const ${moduleName} =  {
path: '/${moduleName}',
name: '${moduleName}',
meta: { title: '${moduleName}', icon: 'code' },
component: RouteView,
redirect: '/${moduleName}/${entityName}/index',
children: [
    {
    path: '/${moduleName}/${entityName}/index',
    name: '${entityName}Manager',
    component: () => import('@/views/modules/${moduleName}/${entityName}/${entityName}Manager'),
    meta: { title: '${entity.description}', keepAlive: false, icon: bxAnaalyse, permissions:[]}
    }
]
}
