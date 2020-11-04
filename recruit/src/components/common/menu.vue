<template>
  <header style="background: #1C86EE">
    <div class="contain" >
      <div>
        <span @click="redirect(1)" class="tab">首页</span>
        <span v-if="isHr" @click="changeStatus" class="tab">发布职位</span>
        <span v-if="isHr" @click="openExplainMeetingDialog" class="tab">发布宣讲</span>

        <span v-if="isHr && !isShow" @click="redirect(6)" class="tab">个人中心</span>
        <span @click="redirect(2)" class="tab" v-if="!isHr && !isAdmin && !isShow">个人中心</span>
        <span @click="redirect(7)" class="tab" v-if="isAdmin && !isShow">个人中心</span>
        <span class="tab" v-if="!isHr"><el-input placeholder="搜索心仪的职位" style="width:18rem" v-model="content"
                                                 @change="getJob(content)"><i slot="prefix"
                                                                              class="el-input__icon el-icon-search"></i></el-input></span>
      </div>
      <div>
        <!-- <span @click="redirect(3)" class="tab" v-show="!isShow">
          <i class="el-icon-message" style="margin-right:0.3rem" @click="redirect(5)">
            </i>消息中心<span class="icon" v-show="count > 0" ref="icon">{{ count }}</span>
          </span> -->
        <span v-if="isShow">
          <span class="tab" @click="redirect(4)">登录</span>
          <span class="tab"  @click="toregister">注册</span>
        </span>
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
              <el-checkbox-group v-model="publishInfo.tags" :checked="checked" @change="checked=!checked">
                  <el-checkbox v-for="item in skillOptions" :label="item" :key="item">{{item}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item>
              <el-button @click="addjob('publishInfo')">确定</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>


        <el-dialog title="发布宣讲" :visible.sync="publishExplainMeetingVisible">
          <el-form :model="explainMeetingInfo" :rules="explainMeetingRules" ref='explainMeetingInfo'>
            <el-form-item label="宣讲会标题" prop="title" class="jobinput">
              <el-input class="require" v-model="explainMeetingInfo.title"></el-input>
            </el-form-item>
            <el-form-item label="宣讲会简介" prop="introduction" class="jobinput">
              <el-input type="textarea" rows="10" class="require" v-model="explainMeetingInfo.introduction"></el-input>
            </el-form-item>

            <el-form-item label="宣讲会海报" prop="posterUrl" class="jobinput">
              <el-input type="textarea" rows="10" class="require" v-model="explainMeetingInfo.posterUrl"></el-input>
            </el-form-item>

            <el-form-item label="宣讲会时间" prop="dateRange" class="jobinput">
               <el-date-picker
                  v-model="explainMeetingInfo.dateRange" style="width: 80%"
                  type="datetimerange"
                  range-separator="至"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="直播二维码" prop="qrCode" class="jobinput">
               <el-input type="textarea" rows="10" class="require" v-model="explainMeetingInfo.qrCode"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button @click="saveExplainMeeting('explainMeetingInfo')">确定</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>


        <span v-if="!isShow" class="tab" @click="logout()" >退出登录</span>
      </div>
    </div>
  </header>
</template>
<style>
  html * {
    padding: 0;
    margin: 0;
  }
  * {
    box-sizing: border-box;
  }
  header {
    width: 100%;
    height: 60px;
    background: rgba(0,0,0,0.8);
  }
  .contain {
    display: flex;
    justify-content: space-between;
    width: 1000px;
    margin: auto;
    line-height: 60px;
    font-weight: 500;
  }
  .contain .tab{
    color: white;
    font-size: 16px;
    margin: 10px;
    padding: 6px;
    border-radius: 4px;
  }
  .icon {
    position: relative;
    background: red;
    font-size: 10px;
    border-radius: 50%;
    left: 0;
    top: -8px;
    padding: 0 5px;
    color: #fff;
  }

  .requireinput {
    width: 35%;
    height: 40px;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    outline: 0;
    background: #fff;
    padding: 0 15px;
    margin: auto 11.2px 14px auto;
  }

  .requireselect {
    width: 35%;
    height: 40px;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    outline: 0;
    background: #fff;
    padding: 0 15px;
    margin: auto 11.2px 14px auto;
  }

  .require {
    width: 80%;
  }

  .addbtn {
    position: relative;
    top: 40px;
    left: 280px;
  }

  .delete {
    color: #dcdfe6;
    position: relative;
    left: -10px;
  }

  .delete:hover {
    color: red;
  }

</style>
<script>/* eslint-disable standard/object-curly-even-spacing */

import fetch from '../../api/fetch'

export default {
  data () {
    var checktitle = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('职位名称不能为空'))
      } else {
        callback()
      }
    }
    var checkExplainMeetingTitle = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('宣讲会标题不能为空'))
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
    var checkExplainMeetingIntroduce = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('宣讲会介绍不能为空'))
      } else {
        callback()
      }
    }
    var checkposterUrl = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('宣讲会海报不能为空'))
      } else {
        callback()
      }
    }
    var checkQrCode = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('宣讲会二维码不能为空'))
      } else {
        callback()
      }
    }

    var checkskill = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('技术要求不能为空'))
      } else {
        callback()
      }
    }

    var checkDateRange = (rule,value,callback) => {        
      if(!value[0] || !value[1]){
          callback(new Error("请选择宣讲会时间"))
      }else{
          callback()
      }
    }    
    return {
      index: 0,
      count: 0,
      amount: 0,
      websocket: null,
      skillOptions: [],
      publishInfo: {
        name: '',
        introduction: '',
        temptation: '',
        workAddress: '',
        education: '',
        tags: [],
        tag: '',
      
      },

      explainMeetingInfo: {
        title: '',
        introduction: '',
        posterUrl: '',
        startDate: null,
        endDate: null,
        qrCode: null,
        dateRange: []
      },

      publishvisible: false,
      publishExplainMeetingVisible: false,
      isHr: false,
      isAdmin: false,
      content: '',
      companyList: [],
      msg: '',
      isShow: true,
      publishRules: {
        name: [{validator: checktitle, trigger: 'blur'}],
        introduction: [{validator: checkintroduce, trigger: 'blur'}]
      },
      explainMeetingRules: {
        title: [{validator: checkExplainMeetingTitle, trigger: 'blur'}],
        introduction: [{validator: checkExplainMeetingIntroduce, trigger: 'blur'}],
        posterUrl: [{validator: checkposterUrl, trigger: 'blur'}],
        qrCode: [{validator: checkQrCode, trigger: 'blur'}],
        dateRange: [{validator: checkDateRange, trigger: 'blur'}]
      }
    }
  },
  created () {
    this.initWs();
  },
  watch: {
    amount () {
      location.reload()
    }
  },
  mounted () {
    if (sessionStorage.getItem('userId')) {
      this.isShow = false
    }
    if (localStorage.getItem('role') === 'E') {
      console.log('HR');
      this.isHr = true
    }else if(localStorage.getItem('role') === 'A'){
      this.isAdmin = true;
    }
    this.getJobName();
  },
  methods: {
    initWs () {
      if (sessionStorage.getItem('userId') !== null) {
        if ('WebSocket' in window) {
          this.websocket = new WebSocket('ws://pf.stalary.com/push/ws/' + `${sessionStorage.getItem('userId')}`, [])
        } else {
          alert('浏览器不支持WebSocket')
        }
        this.websocket.onopen = this.openWS
        this.websocket.onmessage = this.receiveWSMessage
        this.websocket.onclose = this.closeWS
      }
    },
    openWS (e) {
      console.log('建立连接')
    },
    receiveWSMessage (e) {
      console.log('接收消息' + e.data)
      this.count = parseInt(e.data)
    },
    closeWS (e) {
      console.log('关闭连接')
    },
    redirect (num) {
      if (num === 1) {
        this.$router.push({name: 'index'})
      } else if (num === 2) {
        this.$router.push({name: 'userInfo'})
      } else if (num === 3) {
        this.$router.push({name: 'infoCenter'})
      } else if (num === 4) {
        this.$router.push({name: 'login'})
      } else if (num === 5) {
        this.$router.push({name: 'infoCenter'})
      } else if (num === 6) {
        this.$router.push({name: 'hrView'})
      } else if (num === 7) {
        this.$router.push({name: 'adminView'})
      }
    },
    toregister () {
      this.$router.push({name: 'register'})
    },
    getJob (value) {
      if (value !== null) {
        localStorage.setItem('content', value)
      }
      this.$router.push({name: 'search', params: {count: 1}})
    },
    logout () {
      sessionStorage.removeItem('userId')
      localStorage.removeItem('role')
      localStorage.removeItem('token')
      localStorage.removeItem('count')
      this.$router.push({name: 'login'})
    },
    addjob (formName) {
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
              this.amount++
              this.$refs[formName].resetFields()
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

    saveExplainMeeting (formName){
      
      let _this = this;

      _this.publishExplainMeetingVisible = false
      // this.publishInfo.hrId = sessionStorage.getItem('userId')
      // this.publishInfo.companyId = localStorage.getItem('companyId')
      _this.$refs[formName].validate(valid => {
        if (valid) {
          _this.explainMeetingInfo.startDate = _this.explainMeetingInfo.dateRange[0];
          _this.explainMeetingInfo.endDate = _this.explainMeetingInfo.dateRange[1];

          fetch.saveExplainMeeting(_this.explainMeetingInfo).then(res => {
            let data = JSON.parse(res.data);
            console.log(data);
            if (data.result) {
              _this.$message({
                message: '保存成功',
                type: 'success'
              })
              _this.amount++
              _this.$refs[formName].resetFields()
            }else{
              _this.$message({
                message: data.message,
                type: 'error'
              })
              _this.publishExplainMeetingVisible = true;
            }
          }).catch(e => {
            console.log(e)
          })
        }
      })
    },

    deleteItem (key) {
      this.publishInfo.skillList.splice(key, 1)
    },
    addskill () {
      let newskills = {
        weight: 0,
        name: ''
      }
      this.publishInfo.skillList.push(newskills)
    },
    changeStatus() {
      this.publishvisible = true
    },

    openExplainMeetingDialog() {
      this.publishExplainMeetingVisible = true;
    },

    getJobName () {
      let skills = [
        "JAVA", ".net", "android", "ios", "node", "python", 
        "vue", "angular", "C", "C++", "Go", "Linux Shell", "Jquery"
      ]
      this.skillOptions = skills;
      console.log(this.skillOptions);
    },
  }
}
</script>
