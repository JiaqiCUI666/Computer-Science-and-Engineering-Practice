import { createRouter, createWebHistory } from 'vue-router';
import Zhuye from '../pages/zhuye/zhuye.vue';
import Heimingdanzhanshi from '../pages/heimingdanzhanshi/heimingdanzhanshi.vue';
import Xuanchuanzhanshi from '../pages/xuanchuanzhanshi/xuanchuanzhanshi.vue';
import Shangchuanjiance from '../pages/shangchuanjiance/shangchuanjiance.vue';

const routes = [
  {
    path: '/',
    name: 'zhuye',
    component: Zhuye,
  },
  {
    path: '/heimingdanzhanshi',
    name: 'heimingdanzhanshi',
    component: Heimingdanzhanshi,
  },
  {
    path: '/xuanchuanzhanshi',
    name: 'xuanchuanzhanshi',
    component: Xuanchuanzhanshi,
  },
  {
    path: '/shangchuanjiance',
    name: 'shangchuanjiance',
    component: Shangchuanjiance,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;