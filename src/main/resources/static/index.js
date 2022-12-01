angular.module('app',[]).controller("indexController",function ($scope, $http) {
    const constPatch = 'http://localhost:8180/app';

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
    $scope.findPage = function (diffPage){
            var page = document.getElementById("Page").value - 1 + diffPage;
            document.getElementById("Page").value =page + 1;
            $http({
                        url:constPatch + "/findPage",
                        method: "get",
                        params :{
                            page : page,
                            size : 10
                        }
                    })  .then(function (response){
                            $scope.ProductList = response.data;
                                            showProducts();
                        });
        };
        $scope.filterPrice = function (){
                    $http({
                                url:constPatch + "/productsMinMax",
                                method: "get",
                                params : $scope.Price
                            })  .then(function (response){
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
        alert("");
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
                document.getElementById("ProductName").value = response.data.title;
                document.getElementById("ProductPrice").value = response.data.price;
                showFormEdit();
            });
    };
    $scope.deleteProduct = function (productId){
        $http({
            url:constPatch + "/product",
            method: "Delete",
            params :{
                id :productId
            }
        })  .then(function (response){
                $scope.loadProducts();
            });
    };
    $scope.saveProduct = function (){
        console.log($scope.Product);
        $http({
            url : constPatch + "/saveProduct",
            method : "POST",
            params : {
                id : ProductIdEdit,
                title : document.getElementById("ProductName").value,
                price: document.getElementById("ProductPrice").value
            }
        }).then (function (response){
            $scope.loadProducts();
        });
    };

    $scope.loadProducts();
})