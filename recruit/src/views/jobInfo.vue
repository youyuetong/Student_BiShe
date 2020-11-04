<template>
 <div>
   <el-card v-if="isShow" class="jobcard">
     <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png">
     <p>啊哦，职位已经下线了</p>
   </el-card>
   <div v-if="!isShow">
  <el-card class="jobcard" style="height: 200px;">
     <el-image :src="enterpriseInfo.logoUrl" style="width: 150px; height: 150px; float: left; "></el-image>
  <!-- <img :src="company.avatar" class="avatar"> -->
  <div class="introduce">
  <p class="title">{{job.name}}
  <span style="color: green" v-show="isHr && isLogin && job.activeFlg == 2">已发布</span>
  <span style="color: red" v-show="isHr && isLogin && job.activeFlg == 1">未发布</span>

  </p>
  <p>公司名称：{{enterpriseInfo.name}}</p>
  <p>公司地点：{{enterpriseInfo.address}}<span>|</span>成立日期：{{enterpriseInfo.birthDate}}</p>
  <p>公司简介：{{enterpriseInfo.introduction}}</p> 
  
  </div>

  <el-button class="jobbtn" @click="publish(job.id)" v-if="isHr && isLogin && job.activeFlg == 1">发布职位</el-button>
  <el-button class="jobbtn" @click="unpublish(job.id)" v-if="isHr && isLogin && job.activeFlg == 2">下线职位</el-button>
  <el-button class="jobbtn" @click="edit(job.id)" v-if="isHr && isLogin && job.activeFlg == 1">变更职位</el-button>
  <el-button class="jobbtn" @click="sendResume(job.id)" v-if="!isHr && isLogin">投递简历</el-button>
</el-card>
<el-card class="jobcard" >
  <div class="jobintroduce">职位基本信息</div>
  <div class="jobcontent">
  <p>最低教育水平：{{job.education}}</p>
  <p>工作地点:{{job.workAddress}}</p>
  </div>

  <div class="jobintroduce">职位介绍</div>
  <div class="jobcontent">
  <p>{{job.introduction}}</p>
  </div>

  <div class="jobintroduce">职位诱惑</div>
  <div class="jobcontent">
  <p>{{job.temptation}}</p>
  </div>

  <div class="jobintroduce">职位技能</div>
  <div class="jobcontent">
  <p>{{job.tag}}</p>
  </div>

  <div class="jobintroduce">联系hr</div>
  <div class="hrinfo">
    <span><i class="el-icon-news"></i>{{hr.loginName}}</span>

    <span><i class="el-icon-news"></i>微信ID:{{enterpriseInfo.wechatId}}</span>
    <!-- <a :href= "`mailto:${hr.email}`"><span><i class="el-icon-message"></i>{{hr.email}}</span></a> -->
  </div>
</el-card>

<el-dialog title="发布职位" :visible.sync="publishvisible">
    <el-form :model="publishInfo" :rules="publishRules" ref='publishInfo'>
      <el-form-item label="职位名称" prop="name" class="jobinput">
        <el-input class="require" v-model="publishInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="职位介绍" prop="introduction" class="jobinput">
        <el-input type="textarea" rows="10" class="require" v-model="publishInfo.introduction"></el-input>
      </el-form-item>

      <el-form-item label="职位诱惑" prop="temptation" class="jobinput">
        <el-input type="textarea" rows="10" class="require" v-model="publishInfo.temptation"></el-input>
      </el-form-item>

      <el-form-item label="工作地点" prop="workAddress" class="jobinput">
        <el-input type="textarea" rows="10" class="require" v-model="publishInfo.workAddress"></el-input>
      </el-form-item>

      <el-form-item label="学历" prop="education">
        <el-select v-model="publishInfo.education" placeholder="请选择教育水平" style="width:80%">
          <el-option label="大专" value="大专"></el-option>
          <el-option label="本科" value="本科"></el-option>
          <el-option label="研究生" value="研究生"></el-option>
          <el-option label="博士" value="博士"></el-option>
        </el-select>
      </el-form-item>

        <el-form-item label="技术栈" prop="tags">
        <el-checkbox-group v-model="publishInfo.tags" :checked="checked"  @change="checked=!checked">
            <el-checkbox v-for="item in skillOptions" :label="item" :key="item">{{item}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button @click="editjob('publishInfo')">确定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>



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
    var checktitle = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('职位名称不能为空'))
      } else {
        callback()
      }
    }

    var checkintroduce = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('职位介绍不能为空'))
      } else {
        callback()
      }
    }

    return {
      checked:false,
      company: [],
      hr: [],
      job: null,
      enterpriseInfo: null,
      recruit: [],
      recruitId: 0,
      title: '',
      isShow: false,
      publishvisible: false,
      skillOptions: [
        "JAVA", ".net", "android", "ios", "node", "python", 
        "vue", "angular", "C", "C++", "Go", "Linux Shell", "Jquery"
      ],
      publishInfo: {
        id: '',
        name: '',
        introduction: '',
        temptation: '',
        workAddress: '',
        education: '',
        tags: [],
        tag: '',
      
      },
      publishRules: {
        name: [{validator: checktitle, trigger: 'blur'}],
        introduction: [{validator: checkintroduce, trigger: 'blur'}]
      },
    }
  },
  mounted () {
    this.getJobDetail();
   
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
    getJobDetail () {
      let jobId = localStorage.getItem('jobId')
      console.log(jobId);
      fetch
        .getJobDetail(jobId)
        .then(res => {
          let data = JSON.parse(res.data);
          console.log(data);
          if (res.status === 200) {
            if (data.job === null) {
              this.isShow = true
            }


            this.enterpriseInfo = data.enterpriseInfo;
            // this.hr = res.data.data.hr
            this.hr = data.hr;
            this.job = data.job;
            // this.recruitId = this.recruit.id
            // this.title = this.recruit.title
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
    },

    edit (id) {
      let _this = this;
      this.publishvisible = true
      // this.getJobName();
      console.log(this.skillOptions);

      this.$nextTick(function(){
        _this.publishInfo = _this.job;
        _this.publishInfo.tags = _this.job.tag.split(",")
      })
      
       
    },
    getJobName () {
      let skills = [
        "JAVA", ".net", "android", "ios", "node", "python", 
        "vue", "angular", "C", "C++", "Go", "Linux Shell", "Jquery"
      ]
      this.skillOptions = skills;
      console.log(this.skillOptions);
    },

    editjob (formName) {
      this.publishvisible = false

      this.publishInfo.tag = this.publishInfo.tags.join(",");

      // this.publishInfo.hrId = sessionStorage.getItem('userId')
      // this.publishInfo.companyId = localStorage.getItem('companyId')
      this.$refs[formName].validate(valid => {
        if (valid) {
          fetch.saveJob(this.publishInfo).then(res => {
            let data = JSON.parse(res.data);
            console.log(data);
            if (data.result) {
              this.$message({
                message: '保存成功',
                type: 'success'
              })
              location.reload()
              this.amount++
            }else{
              this.$message({
                message: data.message,
                type: 'error'
              })
              this.publishvisible = true;
            }
          }).catch(e => {
            console.log(e)
          })
        }
      })
    },
  },
}
</script>
