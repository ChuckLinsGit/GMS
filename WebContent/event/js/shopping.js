window.onload = function(){//分离javascript和html，必须在js文件中首先声明onload函数，js代码放在onload函数中
   Vue.component('commodity-view',{
    props:['img-src','index'],
    template:'<div class="jumbotron col-md-3" style="padding: 30px 30px 0px 30px;background-color: white;">'+
          '<img data-src="holder.js/200x200" class="img-thumbnail "  alt="200x200" v-bind:src="imgSrc" style="width: 200px; height: 200px;">'+
          '<a href="#" class="btn btn-primary btn-lg " role="button" @click="handleAddToCart" style="margin-left:35px;margin-top: 10px; margin-bottom: 0px;">加入购物车</a>'+
          '</div> ',
    methods:{
      handleAddToCart:function(){
        this.$emit('add-to-cart',this.index)//因为cartList不是该组件的变量，因此需要激活new Vue的方法来添加
      }
    }
  });

  Vue.component('shopping-cart',{
    props:['img-src','index'],
    template:
    '<div class="jumbotron col-md-5 " style="padding: 30px 30px 30px 30px; margin-left: 70px;margin-right: 5px;">'+
        '<div class="row">'+
                '<div class="col-md-8">'+
                  '<img data-src="holder.js/200x200" class="img-thumbnail "  alt="200x200" v-bind:src="imgSrc" style="width: 200px; height: 200px;">'+
                '</div>'+
                '<div class="col-md-4">'+
                  '<div class="row" style="height: 50px">'+
                      '<p><font size="3px">当前商品数量：</font></p>'+
                      '<p style="margin: 0px 0px 0px 50px"><font size="5px">{{count}}</font></p>'+
                  '</div>'+
                  '<div class="row" style="height: 150px">'+
                      '<a href="#" class="btn btn-primary btn-lg " style="margin: 25px 0px 25px 15px;" role="button" v-on:click="handleAddOne()">add one</a>'+
                      '<a href="#" class="btn btn-primary btn-lg " style="margin: 25px 0px 25px 15px;" role="button" v-on:click="handleSubOne(index)">sub one</a>'+
                  '</div>'+
                '</div>'+
        '</div>'+
    '</div> ',
    data:function(){
      return {
        count: 1
      }
    },
    methods:{
      handleAddOne:function(){
        this.count++;
      },
      handleSubOne:function(index){
        this.count--;
        if(this.count<=0){
          this.$emit('delete-commodity',this.index);//因为cartList不是该组件的变量，因此需要激活new Vue的方法来删除
        }
      } 
    }
  });

  new Vue ({
    el:"#container",
    data:{
      //商品浏览图片地址
      commodityList:["imges/1.jpg","imges/2.jpg","imges/3.jpg","imges/4.jpg",
                     "imges/1.jpg","imges/2.jpg","imges/3.jpg","imges/4.jpg",
                     "imges/1.jpg","imges/2.jpg","imges/3.jpg","imges/4.jpg",
                     "imges/1.jpg","imges/2.jpg","imges/3.jpg","imges/4.jpg"],//商品浏览图片地址

      cartList:["imges/1.jpg"]//购物车商品图片地址
    },
    methods:{
      handleAddToCart:function(index){
        this.cartList.push(this.commodityList[index]);
      },
      deleteCommodity:function(index){
        this.cartList.splice(index,1);
      }
    }
  });
 }