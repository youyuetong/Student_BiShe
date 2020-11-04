<template>
 <div>
   <el-card v-if="isShow" class="jobcard">
     <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png">
     <p>啊哦，宣讲会已经不存在咯</p>
   </el-card>
   <div v-if="!isShow">
  <el-card class="jobcard" style="height: 200px;">
  <!-- <img :src="company.avatar" class="avatar"> -->
  <div class="introduce">
  <p class="title">{{explainMeeting.title}}
  <!-- <span style="color: green" v-show="isHr && isLogin && job.activeFlg == 2">已过期</span>
  <span style="color: red" v-show="isHr && isLogin && job.activeFlg == 1">未过期</span> -->

  </p>
  <p>公司名称：{{enterpriseInfo.name}}</p>
  <p>公司地点：{{enterpriseInfo.address}}<span>|</span>成立日期：{{enterpriseInfo.birthDate}}</p>
  <p>公司简介：{{enterpriseInfo.introduction}}</p> 
  
  </div>

  <!-- <el-button class="jobbtn" @click="publish(job.id)" v-if="isHr && isLogin && job.activeFlg == 1">发布职位</el-button>
  <el-button class="jobbtn" @click="unpublish(job.id)" v-if="isHr && isLogin && job.activeFlg == 2">下线职位</el-button> -->
  <!-- <el-button class="jobbtn" @click="sendResume(job.id)" v-if="!isHr && isLogin">投递简历</el-button> -->
</el-card>
<el-card class="jobcard">
  <div class="jobintroduce">宣讲会简介</div>
  <div class="jobcontent">
  <p>{{explainMeeting.introduction}}</p>
  </div>

  <div class="jobintroduce">宣讲会时间</div>
  <div class="jobcontent">
  <p>{{explainMeeting.startDate}}~{{explainMeeting.endDate}}</p>
  </div>

  <div class="jobintroduce">宣讲会海报</div>
  <div class="">
    <!-- style="width: 100px; height: 100px" -->
     <el-image
     
      :src="explainMeeting.posterUrl"
      fit="fill"></el-image>
  </div>

  <div class="jobintroduce">直播二维码</div>
  <div class="">
  <el-image
  style="width: 200px; height: 200px"
      :src="explainMeeting.qrCode"
      fit="fill"></el-image>
  </div>

</el-card>
   </div>
</div>
</template>
<style>
* {
  box-sizing: border-box;
}
body {
  margin: 0;
  padding: 0;
}
div .jobcard {
  width: 1000px;
  margin: 20px auto auto auto;
}
.avatar {
  float: left;
  width: 126px;
  height: 110px;
}
.title {
  font-size: 21px;
}
.introduce {
  margin-left: 140px;
  height: 110px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.introduce p {
  margin: 8px;
}
p span {
  margin: 14px;
}
.jobintroduce {
  border-left: 5px solid #888;
  text-align: left;
  padding-left: 8px;
}
.jobcontent {
  height: 100px;
  margin: 15px auto 15px auto;
  text-align: left;
}
.hrinfo {
  margin:14px auto 42px auto;
  float: left;
}
.hrinfo span{
    margin-right: 14px
}
.jobbtn {
  float: right;
  margin-bottom: 20px;
}
</style>

<script>
import fetch from '../api/fetch'
export default {
  data () {
    return {
      company: [],
      hr: [],
      explainMeeting: null,
      enterpriseInfo: null,
      recruit: [],
      recruitId: 0,
      title: '',
      isShow: false,
    }
  },
  mounted () {
    this.getExplainMeetingDetail()
  },
  computed: {
    isHr() {
      if(localStorage.getItem('role') === 'E') {
      return true
     }
     return false
    },
    isLogin() {
      return localStorage.getItem('token') ? true : false
    }
  },
  methods: {
    getExplainMeetingDetail () {
      let explainMeetingId = localStorage.getItem('explainMeetingId')
      console.log(explainMeetingId);
      fetch
        .viewExplainMeeting(explainMeetingId)
        .then(res => {
          let data = JSON.parse(res.data);
          console.log(data);
          if (res.status === 200) {
            if (data.data === null) {
              this.isShow = true
            }

            this.enterpriseInfo = data.enterpriseInfo;
            this.explainMeeting = data.data;
          }
        })
        .catch(e => {
          console.log(e)
        })
    },
    sendResume (id) {
      
      fetch.deliveryReusme(id).then(res => {
        if (res.status === 200) {
          let data = JSON.parse(res.data);
          if(data.result == true){
            this.$message({
              message: "投递成功",
              type: 'success'
            })
          }else{
            this.$message({
              message: "投递失败",
              type: 'error'
            })
          }
         
        }
      }).catch(e => {
        console.log(e)
      })
    },

    publish (id) {
      fetch.publishJob(id).then(res => {
        let data = JSON.parse(res.data);
        if (data.result) {
          this.$message({
            message: "发布成功",
            type: 'success'
          })
          location.reload() 
        }else{
          this.$message({
            message: "发布失败",
            type: 'error'
          })
        }
      }).catch(e => {
        console.log(e)
      })
    },
    unpublish (id) {
      fetch.unpublishJob(id).then(res => {
        let data = JSON.parse(res.data);
        if (data.result) {
          this.$message({
            message: "下线成功",
            type: 'success'
          })
          location.reload() 
        }else{
          this.$message({
            message: "下线失败",
            type: 'error'
          })
        }
      }).catch(e => {
        console.log(e)
      })
    }
  },
}
</script>
