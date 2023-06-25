import { RouteView } from '@/layouts/index'
import { bxAnaalyse } from '@/core/icons'

export const demo =  {
path: '/demo',
name: 'demo',
meta: { title: 'demo', icon: 'code' },
component: RouteView,
redirect: '/demo/SysDept/index',
children: [
    {
    path: '/demo/SysDept/index',
    name: 'SysDeptManager',
    component: () => import('@/views/modules/demo/SysDept/SysDeptManager'),
    meta: { title: '部门表', keepAlive: false, icon: bxAnaalyse, permissions:[]}
    }
]
}
