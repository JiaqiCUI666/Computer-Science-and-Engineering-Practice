<script setup>
import { reactive, onMounted } from 'vue';
import axios from 'axios';  // 引入axios
import { useRouter } from 'vue-router';


  const props = defineProps({});
const data = reactive({
  blacklist: []  // 用于存储黑名单数据
});

onMounted(async () => {
  try {
    const response = await axios.get('/api/email/get_all_black_list');
    if (response.data.success) {
      data.blacklist = response.data.data;  // 保存黑名单数据
    } else {
      console.error('获取黑名单数据失败', response.data.message);
    }
  } catch (error) {
    console.error('获取黑名单数据失败', error);
  }
});

  const router = useRouter();

  function onClick() {
    router.push({ name: 'zhuye' });
  }

  function onClick_1() {
    router.push({ name: 'shangchuanjiance' });
  }

  function onClick_2() {
    router.push({ name: 'xuanchuanzhanshi' });
  }

  function onClick_3() {
    router.push({ name: 'heimingdanzhanshi' });
  }

</script>

<template>
  <div class="flex-col page">
    <div class="flex-row items-center group">
      <div class="flex-col justify-start shrink-0 text-wrapper">
        <span class="text">智邮卫盾</span>
      </div>
      <span class="font text_2" @click="onClick">主页</span>
      <div class="flex-row shrink-0 group_2">
        <span class="font" @click="onClick_1">上传检测</span>
        <div class="flex-row shrink-0 ml-95">
          <span class="font text_3 " @click="onClick_2">防护提示</span>
          <span class="ml-84 font text_4" @click="onClick_3">黑名单展示</span>
        </div>
      </div>
    </div>
    <img class="self-center image" src="@/image/topbackground.png" />
    <div class="flex-col items-center self-center group_3">
      <span class="text_5">钓鱼邮件发件人黑名单</span>
    </div>
    <div class="flex-col self-stretch group_4">
      <div class="flex-row header-row" style="margin-top: 0;">
        <span class="header-cell">标号</span>
        <span class="header-cell">邮箱号</span>
        <span class="header-cell">IP地址</span>
      </div>
      <div v-for="(item, index) in data.blacklist" :key="index" class="flex-row data-row">
        <span class="data-cell">{{ index + 1 }}</span>
        <span class="data-cell">{{ item.email }}</span>
        <span class="data-cell">{{ item.ip }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  padding: 1.06rem 0 1.69rem;
  background-color: #000000;
  width: 100%;
  overflow-y: auto;
  overflow-x: hidden;
  height: 100%;
}


.data-row {
  display: flex;
  justify-content: space-between;
  padding: 2rem 2.5rem;
  border-bottom: 1px solid #e0e0e0;
}
.header-row {
  display: flex;
  justify-content: space-between;
  padding: 2rem 2.5rem;
  border-bottom: 1px solid #e0e0e0;
  margin-top: 5rem; /* 缩小 "标号、邮箱号、IP地址" 与上方的距离 */
}

.header-cell,
.data-cell {
  flex: 1;
  text-align: center;
  font-family: "Noto Serif SC", serif;
  color: #ffffff;
}

.header-cell {
  font-weight: 700;
  font-size: 1.8rem;
}

.data-cell {
  font-weight: 400;
  font-size: 1.3rem;
}

.artistic-text {
  font-family: "Noto Serif SC", serif;
  font-size: 36px;
  color: #faf1f1;
  text-shadow: 2px 2px 4px #000000;
  letter-spacing: 2px;
}
  .ml-95 {
    margin-left: 5.94rem;
  }
  .page {
    padding: 1.06rem 0 1.69rem;
    background-color: #000000;
    width: 100%;
    overflow-y: auto;
    overflow-x: hidden;
    height: 100%;
  }
  .group {
    margin-left: 5rem;
  }
  .text-wrapper {
    padding: 0.38rem 0 0.75rem;
    border-radius: 0.38rem;
    height: 2.38rem;
  }
  .text {
    margin-left: 0.13rem;
    color: #ffffff;
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
    font-weight: 700;
    line-height: 1.31rem;
  }
  .font {
    font-size: 1.2rem; /* 增大字体大小 */
    font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
    line-height: 0.94rem;
    color: #ffffff;
  }
  .text_2 {
    margin-left: 16.38rem;
  }
  .group_2 {
    margin-left: 7.38rem;
  }
  .text_3 {
    line-height: 0.93rem;
  }
  .text_4 {
    line-height: 0.94rem;
  }
  .image {
    margin-top: 3rem;
    width: 100vw;
    height: 8vw;
  }
  .group_3 {
    margin-top: 5rem;
    
  }
  .text_5 {
    color: #ffffff;
    font-size: 3rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2rem;
  }
  .text_6 {
    color: #ffffff;
    font-size: 1.13rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.14rem;
  }
  .group_4 {
    margin-top: 7.25rem;
  }
  .group_5 {
    padding: 2rem 20rem 1.88rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .font_2 {
    font-size: 1.8rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2.06rem;
    color: #ffffff;
  }
  .group_6 {
    margin-right: 3.63rem;
  }
  .text_7 {
    line-height: 2.07rem;
  }
  .text_8 {
    line-height: 1.98rem;
  }
  .group_7 {
    padding: 2.75rem 20rem 1.75rem 11.75rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_2 {
    width: 0.94rem;
    height: 2.63rem;
  }
  .pos {
    position: absolute;
    left: 11.79rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .font_4 {
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.51rem;
    color: #ffffff;
  }
  .font_3 {
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.38rem;
    color: #ffffff;
  }
  .pos_2 {
    position: absolute;
    right: 11.1rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .group_8 {
    padding: 2.75rem 20rem 1.75rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_3 {
    width: 1.94rem;
    height: 2.69rem;
  }
  .pos_3 {
    position: absolute;
    left: 11.66rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .pos_4 {
    position: absolute;
    right: 11.23rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .group_9 {
    padding: 1.75rem 20rem 1.75rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_4 {
    width: 1.94rem;
    height: 2.63rem;
  }
  .group_10 {
    margin-top: 0.75rem;
  }
  .group_11 {
    padding: 1.75rem 20rem 1.75rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_5 {
    width: 2.19rem;
    height: 2.63rem;
  }
  .group_12 {
    margin-top: 0.75rem;
  }
  .group_13 {
    padding: 1.63rem 10.88rem 1.63rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .group_14 {
    margin-top: 0.75rem;
  }
  .group_15 {
    padding: 1.63rem 10.75rem 1.63rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_6 {
    width: 2.13rem;
    height: 2.75rem;
  }
  .group_16 {
    margin-top: 0.75rem;
  }
  .image_7 {
    width: 2.13rem;
    height: 2.81rem;
  }
  .group_17 {
    margin-top: 0.81rem;
  }
  .group_18 {
    padding: 1.63rem 10.63rem 1.63rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_8 {
    width: 2.19rem;
    height: 2.75rem;
  }
  .group_19 {
    margin-top: 0.75rem;
  }
</style>