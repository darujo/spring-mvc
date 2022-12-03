angular.module('app',[]).controller("indexController",function ($scope, $http) {
    const constPatch = 'http://localhost:8180/app/v1/products';

    var showProducts = function (){
        document.getElementById("ProductList").style.display ="block";
        document.getElementById("FormEdit").style.display ="none";
    };
    var showFormEdit = function (){
        document.getElementById("ProductList").style.display ="none";
        document.getElementById("FormEdit").style.display ="block";
    };

    $scope.loadProducts = function (){
        $scope.findPage(0);
    };
    var Price;
    $scope.findPage = function (diffPage){
            console.log(diffPage);
            var page = parseInt(document.getElementById("Page").value) + diffPage;
            console.log(page);
            document.getElementById("Page").value =page;
            $http({
                        url:constPatch + "",
                        method: "get",
                        params :{
                            page : page,
                            size : 10,
                            min : Price ? Price.min : null,
                            max : Price ? Price.max : null

                        }
                    })  .then(function (response){
                            $scope.ProductList = response.data.content;
                                            showProducts();
                        });

        };
        $scope.filterPrice = function (){
            Price =$scope.Price;
            document.getElementById("Page").value = "1";
                    $scope.findPage(0);
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
        $http.get(constPatch + "/" + productId)
            .then(function (response){
                ProductIdEdit = response.data.id;
                document.getElementById("ProductName").value = response.data.title;
                document.getElementById("ProductPrice").value = response.data.price;
                showFormEdit();
            });
    };
    $scope.deleteProduct = function (productId){
        $http.delete(constPatch + "/" + productId )
            .then(function (response){
                $scope.loadProducts();
            });
    };
    $scope.saveProduct = function (){
        console.log($scope.Product);
        $http({
            url : constPatch + "",
            method : "POST",
            params : {
                id : ProductIdEdit ? ProductIdEdit : null,
                title : document.getElementById("ProductName").value,
                price: document.getElementById("ProductPrice").value
            }
        }).then (function (response){
            $scope.loadProducts();
        });
    };

    $scope.loadProducts();
})