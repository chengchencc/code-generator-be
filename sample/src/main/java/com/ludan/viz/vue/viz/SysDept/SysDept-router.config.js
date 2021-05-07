import { RouteView } from '@/layouts/index'
import { bxAnaalyse } from '@/core/icons'

export const viz =  {
path: '/viz',
name: 'viz',
meta: { title: 'viz', icon: 'code' },
component: RouteView,
redirect: '/viz/SysDept/index',
children: [
    {
    path: '/viz/SysDept/index',
    name: 'SysDeptManager',
    component: () => import('@/views/modules/viz/SysDept/SysDeptManager'),
    meta: { title: '部门表', keepAlive: false, icon: bxAnaalyse, permissions:[]}
    }
]
}
