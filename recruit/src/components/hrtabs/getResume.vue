<template>
  <div class="getResumeWrap">
    <div v-if="show" class="nofind">
      <img
        src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png" style="margin: 0 auto">
      <p>暂时没有记录哦</p>
    </div>
    <div v-for="(item, index) in list" :key="index" v-if="!show">
      <el-card shadow="hover" class="receiveBox">
        <div class="flex" >
          <el-progress :width="80" type="circle" :percentage="item.rate" color="#A6F6AF" class="circle"></el-progress>
          <span class="pipei">简历匹配度</span>
          <p class="receiveInfo"  style="width: 60%">收到<b>{{item.jobHunter.name}}</b>的<b>{{item.jobName}}</b>求职信息<br>
          
            <el-tag type="warning" effect="dark" v-if="item.canInterview == null || item.canInterview == ''">未处理</el-tag>
            <el-tag type="success" effect="dark" v-if="item.canInterview == 'I'">已邀请</el-tag>
            <el-tag type="danger" effect="dark" v-if="item.canInterview == 'R'">不邀请</el-tag>

            <el-tag type="success" effect="dark" v-if="item.canInterview == 'I' && item.acceptInterview == 'Y'">参加面试</el-tag>
            <el-tag type="danger" effect="dark" v-if="item.canInterview == 'I' && item.acceptInterview == 'N'">不参加面试</el-tag>

            <el-tag type="success" effect="dark" v-if="item.passFlag == 'Y'">通过面试</el-tag>
            <el-tag type="danger" effect="dark" v-if="item.passFlag == 'N'">面试淘汰</el-tag>

            <el-tag type="info" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == null || item.signFlag == '')">未发起云签约</el-tag>
            <el-tag type="warning" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == '1')">发起云签约</el-tag>
            <el-tag type="danger" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == '2')">拒绝云签约</el-tag>
            <el-tag type="success" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == '3')">完成云签约</el-tag>
          </p>
          
          <div style="width: 230px; text-align: left;">
         
          <el-button v-if="item.canInterview == null || item.canInterview == ''"
             @click="invitation(item.id)" class="clickbtn" style="margin-top: 0px;  margin-left: 0px; width: 100px">邀请面试</el-button>
          <el-button v-if="item.canInterview == null || item.canInterview == ''"
             @click="uninvitation(item.id)" class="clickbtn" style="margin-top: 0px; margin-left: 0px; width: 100px; text-align: center; padding-left: 13px">不邀请面试</el-button>

           <el-button v-if="item.canInterview == 'I' && item.acceptInterview == 'Y' && (item.passFlag == null || item.passFlag == '')"
             @click="passInterview(item.id)" class="clickbtn" style="margin-top: 0px;  margin-left: 0px; width: 100px">面试通过</el-button>
          <el-button v-if="item.canInterview == 'I' && item.acceptInterview == 'Y' && (item.passFlag == null || item.passFlag == '')"
             @click="unpassInterview(item.id)" class="clickbtn" style="margin-top: 0px; margin-left: 0px; width: 100px; text-align: center; padding-left: 13px">面试淘汰</el-button>

          <el-button v-if="item.passFlag == 'Y' && (item.signFlag == null || item.signFlag == '')"
             @click="cloudSign(item.id)" class="clickbtn" style="margin-top: 0px;  margin-left: 0px; width: 100px">发起云签约</el-button>

           <el-button @click="getTableList(item.hunterId)" class="clickbtn" style="margin-top: 0px;  margin-left: 0px; width: 100px">查看</el-button>
          </div>
          
        </div>
        <p class="receive">{{item.time}}</p>
      </el-card>
      <el-dialog :title="getResumeList.name+'的简历'" :visible.sync="getResumev">
        <table border="1" cellspacing="0" style="border-color:#ededed" class="mytable">
          <tr>
            <td>姓名：</td>
            <td>{{getResumeList.name}}</td>
          </tr>
          <tr>
            <td>出生日期：</td>
            <td>{{getResumeList.birthDate}}</td>
          </tr>
          <tr>
            <td>性别：</td>
            <td>{{getResumeList.gender}}</td>
          </tr>
          <tr>
            <td>电话：</td>
            <td>{{getResumeList.mobile}}</td>
          </tr>
          <tr>
            <td>邮箱：</td>
            <td>{{getResumeList.email}}</td>
          </tr>
          <tr>
            <td>自我介绍：</td>
            <td>{{getResumeList.selfIntroduction}}</td>
          </tr>
          <tr>
            <td>技术栈：</td>
            <td>{{getResumeList.tag}}</td>
          </tr>
          <tr>
            <td>毕业院校：</td>
            <td>{{getResumeList.collegeName}}</td>
          </tr>
          <tr>
            <td>教育水平：</td>
            <td>{{getResumeList.education}}</td>
          </tr>

           <tr>
            <td> 教育经历：</td>
            <td>
              <el-table :data="getResumeList.educationExList" :row-class-name="tableRowClassName" style="width: 100%">
                <el-table-column prop="experienceContent"  label="经历" >
                </el-table-column>
                <el-table-column prop="startDate" label="开始时间" >
                </el-table-column>
                <el-table-column prop="endDate"  label="结束时间">
                </el-table-column>
              </el-table>
            </td>
          </tr>  

          <tr>
            <td> 项目经历：</td>
            <td>
              <el-table :data="getResumeList.projectExList" :row-class-name="tableRowClassName" style="width: 100%" >
                <el-table-column prop="experienceContent"  label="经历" >
                </el-table-column>
                <el-table-column prop="startDate" label="开始时间" >
                </el-table-column>
                <el-table-column prop="endDate"  label="结束时间">
                </el-table-column>
              </el-table>
            </td>
          </tr>  

          <tr>
            <td> 工作经历：</td>
            <td>
              <el-table :data="getResumeList.workExList" :row-class-name="tableRowClassName" style="width: 100%" >
                <el-table-column prop="experienceContent"  label="经历" >
                </el-table-column>
                <el-table-column prop="startDate" label="开始时间" >
                </el-table-column>
                <el-table-column prop="endDate"  label="结束时间">
                </el-table-column>
              </el-table>
            </td>
          </tr>  
        </table>
      </el-dialog>
    </div>

    <div class="block" style="margin-bottom: 30px;">
        <el-pagination
          layout="prev, pager, next"
          :total="paging.total" @current-change="handleCurrentChange">
        </el-pagination>
      </div>
  </div>
</template>
<style>
p span {
    /* margin: 14px; */
}
  .getResumeWrap {
    height: 1000px;
    overflow: scroll;
  }

  .getResumeWrap::-webkit-scrollbar {
    display: none;
  }

  .progress2 {
    width: 182px;
    border: 0;
  }

  .mytable {
    width: 100%;
    height: 700px;
  }

  .clickbtn {
    height: 35px;
    margin-top: 14px;
  }

  .pipei {
    position: relative;
    top: 82px;
    left: -76px;
  }

  .flex {
    display: flex;
    justify-content: flex-start;
  }

  .receiveInfo {
    margin: 28px auto auto 14px;
  }

  .receive {
    position: relative;
    left: 330px;
  }

  .receiveBox {
    margin-bottom: 14px;
    width: 100%;
  }

  .nofind p {
    font-size: 18px;
    color: #909399;
  }

  .nofind img {
    width: 350px;
    height: 280px;
    margin: 28px 28rem auto auto;
  }

  .el-dialog {
    position: relative;
    margin: 0 auto 50px;
    border-radius: 2px;
    -webkit-box-shadow: 0 1px 3px rgba(0,0,0,.3);
    box-shadow: 0 1px 3px rgba(0,0,0,.3);
    box-sizing: border-box;
    width: 60%;
  }
</style>

<script>
  import fetch from '../../api/fetch'

  export default {
    data() {
      return {
        getResumeList: {
          name: '',
          sex: '',
          age: '',
          skills: [{
            id: 1,
            name: '',
            level: '',
            resumeId: 1
          }],
          school: '',
          address: '',
          endTime: 2019,
          phone: '',
          email: '',
          introduce: '',
          experience: '',
          awards: '',
          avatar: '',
          educationExList: [],
          workExList: [],
          projectExList: []
        },
        list: [],
        show: false,
        getResumev: false,
        paging: {
          all: null, 			//总页数
          cur: 1, 			//当前页码
          page_size: 10, 		//每页显示数
          total: 0,
          list: [],
        },
      }
    },
    mounted() {
      this.getList()
    },
    methods: {
      getList() {
        let _this = this;
        let param = {
          // "keyword": this.content.toLowerCase()
        }

        fetch.receiveResume(param, null).then(res => {
          //  let data = JSON.parse(res.data);
          //   console.log(data);
          //   if (res.status === 200) {
          //     // this.list = res.data.data.receiveList
          //     // if (this.list.length === 0) {
          //     //   this.show = true
          //     // }
          //   }
            let data = JSON.parse(res.data);
            console.log(data);
            if (res.status === 200) {
              _this.list = data.queryresult
              _this.havejob = true

              _this.paging.list = data.queryresult;
              _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
              _this.paging.total = data.recordsFiltered;
              console.log(_this.paging);
            }
        }).catch(e => {
          console.log(e)
        })
      },

      handleCurrentChange: function(e){
          console.log(e);
          let start = ((e-1) * this.paging.page_size) + 1
          let _this = this;
          let param = {
            // "keyword": this.content.toLowerCase()
          }
          fetch
            .receiveResume(param, start)
            .then(res => {
              let data = JSON.parse(res.data);
              console.log(data);
              if (res.status === 200) {
                _this.list = data.queryresult
                _this.havejob = true

                _this.paging.list = data.queryresult;
                _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
                _this.paging.total = data.recordsFiltered;
                console.log(_this.paging);
              }
            })
            .catch(e => {
              console.log(e)
            })
        },

      getTableList(id) {
        this.getResumev = true
        fetch
          .getResume(id)
          .then(res => {
            let data = JSON.parse(res.data);

            if (res.status === 200) {
              // if (res.data.success === true) {
                if (res.data.data !== null) {
                  this.getResumeList = data.jobHunter;
                  console.log(this.getResumeList);
                   this.getResumeList.projectExList = data.projectExList;
                  this.getResumeList.workExList = data.workExList;
                  this.getResumeList.educationExList = data.educationExList
                }
              // }
            }
          })
          .catch(e => {
            console.log(e)
          })
      },
tableRowClassName ({row, rowIndex}) {
  debugger;
  //把每一行的索引放进row
  row.index = rowIndex;
},
      invitation: function(id){
         let _this = this;

          _this.$prompt('请输入面试须知', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(({ value }) => {
             let param = {
              "interviewDate": value
            }

            fetch
            .invitation(id, param)
            .then(res => {
              let data = JSON.parse(res.data);

              if (res.status === 200) {
                // if (res.data.success === true) {
                  if (data.result) {
                    _this.$message.success('操作成功')
                    _this.getList();
                  }else{
                    _this.$message.error('操作失败')
                  }
                // }
              }
            })
            .catch(e => {
              console.log(e)
            })
          
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '取消输入'
            });       
          });


          
      },
      uninvitation: function(id){
          fetch
          .uninvitation(id)
          .then(res => {
            let data = JSON.parse(res.data);

            if (res.status === 200) {
              // if (res.data.success === true) {
                if (data.result) {
                  this.$message.success('操作成功')
                  this.getList();
                }else{
                   this.$message.error('操作失败')
                }
              // }
            }
          })
          .catch(e => {
            console.log(e)
          })
      },

      passInterview: function(id){
        let _this = this;
          fetch
          .passInterview(id, "Y")
          .then(res => {
            let data = JSON.parse(res.data);

            if (res.status === 200) {
              // if (res.data.success === true) {
                if (data.result) {
                  _this.$message.success('操作成功')
                  _this.getList();
                }else{
                   _this.$message.error('操作失败')
                }
              // }
            }
          })
          .catch(e => {
            console.log(e)
          })
      },

      unpassInterview: function(id){
        let _this = this;
          fetch
          .passInterview(id, "N")
          .then(res => {
            let data = JSON.parse(res.data);

            if (res.status === 200) {
              // if (res.data.success === true) {
                if (data.result) {
                  _this.$message.success('操作成功')
                  _this.getList();
                }else{
                   _this.$message.error('操作失败')
                }
              // }
            }
          })
          .catch(e => {
            console.log(e)
          })
      },

      cloudSign: function(id){
          let _this = this;
          fetch
          .cloudSign(id, "1")
          .then(res => {
            let data = JSON.parse(res.data);

            if (res.status === 200) {
              // if (res.data.success === true) {
                if (data.result) {
                  _this.$message.success('操作成功')
                  _this.getList();
                }else{
                   _this.$message.error('操作失败')
                }
              // }
            }
          })
          .catch(e => {
            console.log(e)
          })
      }
    }
  }
</script>
