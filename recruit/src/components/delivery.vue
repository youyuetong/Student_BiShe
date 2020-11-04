<template>
  <div class="deliveryWrap">
    <div v-if="!show" class="nofind">
      <img
        src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png" style="margin: 0 auto">
      <p>暂时没有记录哦</p>
    </div>
    <div v-for="(item, index) in list" :key="index" v-if="show">
      <el-card shadow="hover" class="deliverycard" style="height: 180px">
        <div class="resumeBox">
          <p>投递职位：{{item.jobName}}</p>
          <p>投递公司：{{item.companyName}}</p>
          <p>投递时间：{{item.createTime}}</p>
        </div>
        <div class="resumeBox">
          <p>
          <el-tag type="warning" effect="dark" v-if="item.canInterview == null || item.canInterview ==''">未查看</el-tag>
          <el-tag type="success" effect="dark" v-if="(item.canInterview != null && item.canInterview !='') && item.canInterview == 'I' ">邀请面试</el-tag>
          <el-tag type="danger" effect="dark" v-if="(item.canInterview != null && item.canInterview !='') && item.canInterview == 'R' ">不邀请面试</el-tag>
          
          <el-tag type="success" effect="dark" v-if="item.canInterview == 'I' && item.acceptInterview == 'Y'">参加面试</el-tag>
          <el-tag type="danger" effect="dark" v-if="item.canInterview == 'I' && item.acceptInterview == 'N'">不参加面试</el-tag>
          
          <el-tag type="info" effect="dark" v-if="item.acceptInterview == 'Y' && (item.passFlag == '' || item.passFlag == null)">未公布面试结果</el-tag>
          <el-tag type="success" effect="dark" v-if="item.acceptInterview == 'Y' && item.passFlag == 'Y'">面试通过</el-tag>
          <el-tag type="danger" effect="dark" v-if="item.acceptInterview == 'Y' && item.passFlag == 'N'">面试失败</el-tag>

           <el-tag type="info" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == null || item.signFlag == '')">未发起云签约</el-tag>
            <el-tag type="warning" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == '1')">发起云签约</el-tag>
            <el-tag type="danger" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == '2')">拒绝云签约</el-tag>
            <el-tag type="success" effect="dark" v-if="item.passFlag == 'Y' && (item.signFlag == '3')">完成云签约</el-tag>
          </p>
          <p v-if="item.canInterview != null">邀请备注：{{item.interviewTime}}</p>
        </div>

        <div v-if="(item.canInterview != null && item.canInterview !='')" class="resumeBox" style="float: left;">
            <el-button class="jobbtn"  @click="checkDetail(item.jobId)">查看职位</el-button>
            <el-button class="jobbtn" v-if="item.acceptInterview == 'Y' && (item.passFlag == 'Y' || item.passFlag == 'N')" @click="evalution(item.id)">评价面试</el-button>

            <el-button class="jobbtn" @click="receiveInterview(item.id)" v-if="item.canInterview == 'I' && (item.acceptInterview == null || item.acceptInterview == '')">接受面试</el-button>
            <el-button class="jobbtn" @click="unreceiveInterview(item.id)" v-if="item.canInterview == 'I' && (item.acceptInterview == null || item.acceptInterview == '')">拒绝面试</el-button>
            
            <el-button class="jobbtn" @click="finishSign(item.id)" v-if="item.passFlag == 'Y' && (item.signFlag == '1')">完成云签约</el-button>
            <el-button class="jobbtn" @click="rejectSign(item.id)" v-if="item.passFlag == 'Y' && (item.signFlag == '1')">拒绝云签约</el-button>

        </div>

         <div v-if="item.acceptInterview == 'Y'" class="resumeBox">
            <p>面试评价：{{item.interviewEvaluation}}</p>
            <p>评价时间：{{item.evaluationTime}}</p>
        </div>
        <div v-if="item.signFlag == '3'" class="resumeBox">
            <p>云签约时间：{{item.signDate}}</p>
        </div>
      </el-card>
    </div>

    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="paging.total" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>
<style>
  .deliveryWrap {
    height: 1000px;
    overflow: scroll;
  }

  .deliveryWrap::-webkit-scrollbar {
    display: none;
  }

  .resumeBox {
    display: flex;
    justify-content: space-between;
  }

  .deliverycard {
    margin-bottom: 14px;
    width: 100%;
    height: 100px;
    border-left: 5px solid #888;
  }

  .nofind p {
    font-size: 18px;
    color: #909399;
  }

  .nofind img {
    width: 350px;
    height: 280px;
    margin: 28px 28px auto auto;
  }
</style>

<script>
  import fetch from '../api/fetch'

  export default {
    data() {
      return {
        list: [],
        show: true,
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
      checkDetail(id) {
        localStorage.setItem('jobId', id)
        this.$router.push({name: 'jobInfo'})
      },
      getList() {
        let _this = this;
        let param = {
          // "keyword": this.content.toLowerCase()
        }
        fetch
          .deliveryList(param, null)
          .then(res => {
            let data = JSON.parse(res.data);
            console.log(data);
            if (res.status === 200) {
              console.log("important");
              console.log(data.queryresult);
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


        // fetch
        //   .deliveryList()
        //   .then(res => {
        //     if (res.status === 200) {
        //       if (res.data.data === null) {
        //         this.show = false
        //       } else {
        //         this.list = res.data.data.sendList ? res.data.data.sendList : []
        //       }
        //     }
        //   })
        //   .catch(e => {
        //     console.log(e)
        //   })
      },

      handleCurrentChange: function(e){
          console.log(e);
          let start = ((e-1) * this.paging.page_size) + 1
          let _this = this;
          let param = {
            // "keyword": this.content.toLowerCase()
          }
          fetch
            .deliveryList(param, start)
            .then(res => {
              let data = JSON.parse(res.data);
              console.log(data);
              if (res.status === 200) {
                console.log("important");
                console.log(data.queryresult);
                _this.list = data.queryresult
                _this.havejob = true

                _this.paging.list = data.queryresult;
                _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
                _this.paging.total = data.recordsFiltered;
                console.log(_this.paging);
              }


              // if (res.status === 200) {
              //   if (res.data.success === true) {
              //     if (res.data.data.recruitList.length !== 0) {
              //       this.list = res.data.data.recruitList
              //       this.havejob = true
              //     }
              //   }
              // }
            })
            .catch(e => {
              console.log(e)
            })
        },
        receiveInterview: function(id){
          console.log("接受面试");
          let _this = this;
           fetch
            .receiveInterview(id, "Y")
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
        unreceiveInterview: function(id){
          let _this = this;
          console.log("不接受面试");
          fetch
            .receiveInterview(id, "N")
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

        evalution: function(id){
           let _this = this;

          _this.$prompt('评价面试过程', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(({ value }) => {
             let param = {
              "interviewEvaluation": value
            }

            fetch
            .evaluationInterview(id, param)
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
               
          }); 
        },

        finishSign: function(id){
          let _this = this;
          fetch
          .cloudSign(id, "3")
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

        rejectSign: function(id){
          let _this = this;
          fetch
          .cloudSign(id, "2")
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
