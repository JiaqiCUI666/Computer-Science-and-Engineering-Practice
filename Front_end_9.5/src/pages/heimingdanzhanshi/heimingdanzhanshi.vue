<script setup>
  import { useRouter } from 'vue-router';
  import { reactive, onMounted } from 'vue';

  const props = defineProps({});

  const data = reactive({});

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
    <div class="flex-row items-center  group">
      <div class="flex-col justify-start shrink-0 text-wrapper"><span class="text">智邮卫盾</span></div>
      <span class="font text_2"  @click="onClick">主页</span>
      <div class="flex-row shrink-0 group_2">
        <span class="font" @click="onClick_1">上传检测</span>
        <div class="flex-row shrink-0 ml-95">
          <span class="font text_3 " @click="onClick_2">防护提示</span>
          <span class="ml-84 font text_4" @click="onClick_3">黑名单展示</span>
        </div>
      </div>
    </div>
    <img
      class="self-center image"
      src="@/image/topbackground.png"
    />
    
    
    
    <div class="table-container">
      <span class="table-title">钓鱼邮件发件人黑名单</span>
      <table class="styled-table">
        <thead>
          <tr>
            <th>标号</th>
            <th>邮箱号</th>
            <th>IP地址</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>john.doe123@gmail.com</td>
            <td>172.16.254.1</td>
          </tr>
          <tr>
            <td>2</td>
            <td>alice_wang2024@hotmail.com</td>
            <td>10.0.0.1</td>
          </tr>
          <tr>
            <td>3</td>
            <td>charlie_brown99@outlook.com</td>
            <td>192.168.10.15</td>
          </tr>
          <tr>
            <td>4</td>
            <td>sophia.lee789@yahoo.com</td>
            <td>203.0.113.10</td>
          </tr>
          <tr>
            <td>5</td>
            <td>mike.ross567@company.org</td>
            <td>198.51.100.5</td>
          </tr>
          <tr>
            <td>6</td>
            <td>emily_smith2023@mail.com</td>
            <td>10.20.30.40</td>
          </tr>
          <tr>
            <td>7</td>
            <td>robert.jones88@domain.net</td>
            <td>192.168.50.25</td>
          </tr>
          <tr>
            <td>8</td>
            <td>lucas.martin456@university.edu</td>
            <td>198.51.100.42</td>
          </tr>
          <tr>
            <td>9</td>
            <td>olivia.wilson789@random.biz</td>
            <td>203.0.113.77</td>
          </tr>
          <tr>
            <td>10</td>
            <td>daniel.green101@service.co</td>
            <td>192.168.0.12</td>
          </tr>

        </tbody>
      </table>
    </div>

  </div>
</template>

<style scoped lang="css">
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
    font-family: "Noto Sans", sans-serif; /* 使用黑体字体 */
    font-weight: 700;
    line-height: 1.31rem;
  }
  .font {
    font-size: 1.2rem; /* 增大字体大小 */
    font-family: "Noto Sans", sans-serif; /* 使用黑体字体 */
    line-height: 0.94rem;
    color: #ffffff;
  }
  .font:hover {
    color: #7c9bcd;
    font-family: "Noto Sans", sans-serif;
    transform: scale(1.1);
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
    margin-top: 6.5rem;
  }
  .text_5 {
    color: #ffffff;
    font-size: 3rem;
    font-family: "Noto Serif", sans-serif;
    line-height: 2.7rem;
  }
  .text_6 {
    color: #ffffff;
    font-size: 1.13rem;
    font-family: "Noto Serif", sans-serif;
    line-height: 1.14rem;
  }
  .group_4 {
    margin-top: 7.25rem;
  }
  .group_5 {
    padding: 2rem 10.63rem 1.88rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .font_2 {
    font-size: 1.8rem;
    font-family: "Noto Serif", sans-serif;
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
    padding: 2.75rem 11rem 1.75rem 11.75rem;
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
    font-family: "Noto Serif", sans-serif;
    line-height: 1.51rem;
    color: #ffffff;
  }
  .font_3 {
    font-size: 1.5rem;
    font-family: "Noto Serif", sans-serif;
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
    padding: 2.75rem 11.13rem 1.75rem 11.63rem;
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
    padding: 1.75rem 10.88rem 1.75rem 11.63rem;
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
    padding: 1.75rem 10.63rem 1.75rem 11.63rem;
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

  .table-container {
  margin-top: 6.5rem;
  width: 100%;
}

.table-title {
  color: #ffffff;
  font-size: 3rem;
  font-family: "Noto Serif", sans-serif;
  text-align: center; /* 居中对齐文本 */
  margin-bottom: 2rem;
  display: flex; /* 使用 Flex 布局 */
  justify-content: center; /* 水平居中 */
}


.styled-table {
  width: 100%;
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 1.5rem;
  text-align: left;
  color: #ffffff;
}

.styled-table th,
.styled-table td {
  padding: 12px 15px;
}

.styled-table thead tr {
  background-color: #1f4788;
  color: #ffffff;
  text-align: left;
}

.styled-table tbody tr {
  border-bottom: 1px solid #000000;
}

.styled-table tbody tr:nth-of-type(even) {
  background-color: #000000;
}

.styled-table tbody tr:hover {
  background-color: #707374;
}

.styled-table td img {
  width: 2rem;
  height: auto;
}


</style>