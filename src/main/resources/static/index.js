angular.module('app',[]).controller("indexController",function ($scope, $http) {
    const constPatch = 'http://localhost:8180/product';

    var showProducts = function (){
        document.getElementById("ProductList").style.display ="block";
        document.getElementById("FormEdit").style.display ="none";
    };
    var showFormEdit = function (){
        document.getElementById("ProductList").style.display ="none";
        document.getElementById("FormEdit").style.display ="block";
    };

    $scope.loadProducts = function (){
        $http.get(constPatch + "/products")
            .then(function (response){
                $scope.ProductList = response.data;
                showProducts();
            });
    };
    var ProductIdEdit = null;

    $scope.createProduct = function (){
        ProductIdEdit = null;
        document.getElementById("ProductName").value = "";
        document.getElementById("ProductPrice").value = 0;
        showFormEdit();
    };

    $scope.editProduct = function (productId){
        $http({
                url:constPatch + "/editProduct",
                method: "GET",
                params :{
                    id :productId
                      }
        })
            .then(function (response){
                ProductIdEdit = response.data.id;
                document.getElementById("ProductName").value = response.data.name;
                document.getElementById("ProductPrice").value = response.data.price;
                showFormEdit();
            });
    };
    $scope.deleteProduct = function (productId){
        $http({
            url:constPatch + "/deleteProduct",
            method: "GET",
            params :{
                id :productId
            }
        })  .then(function (response){
                $scope.loadProducts();
            });
    };
    $scope.saveProduct = function (){
        $http({
            url : constPatch + "/saveProduct",
            method : "POST",
            params : {
                id : ProductIdEdit,
                name : document.getElementById("ProductName").value,
                price: document.getElementById("ProductPrice").value
            }
        }).then (function (response){
            $scope.loadProducts();
        });
    };

    $scope.loadProducts();
})