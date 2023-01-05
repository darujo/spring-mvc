angular.module('app', ['ngStorage']).controller("indexController", function ($scope, $http, $localStorage) {
    const constPatchAuth    = 'http://localhost:5555';
    const constPatchOrder   = 'http://localhost:5555/order-service/v1';
    const constPatchProduct = 'http://localhost:5555/product-service/v1';
    const constPatchBasket  = 'http://localhost:5555/basket-service/v1/baskets';
    var showProducts = function () {
        document.getElementById("ProductList").style.display = "block";
        document.getElementById("FormEdit").style.display = "none";
    };
    var showFormEdit = function () {
        document.getElementById("ProductList").style.display = "none";
        document.getElementById("FormEdit").style.display = "block";
    };
    if ($localStorage.authUser){
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.authUser.token;
        try {
            let jwt = $localStorage.authUser.token;
            let payLoad = JSON.parse(atob(jwt.split(".")[1]));
            let currTime = parseInt(new Date().getTime() / 1000);
            if(currTime > payLoad.exp){
                console.log("токен просрочен");
                delete $localStorage.authUser;
                $http.defaults.headers.common.Authorization = '';
            }
        }
        catch (e){

        }
    }


    $scope.loadProducts = function () {
        $scope.findPage(0);
    };
    var Price;
    $scope.findPage = function (diffPage) {
        console.log(diffPage);
        var page = parseInt(document.getElementById("Page").value) + diffPage;
        console.log(page);
        document.getElementById("Page").value = page;
        $http({
            url: constPatchProduct + "/products",
            method: "get",
            params: {
                page: page,
                size: 10,
                min: Price ? Price.min : null,
                max: Price ? Price.max : null

            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
            showProducts();
        });

    };
    $scope.filterPrice = function () {
        Price = $scope.Price;
        document.getElementById("Page").value = "1";
        $scope.findPage(0);
    };

    var ProductIdEdit = null;

    $scope.createProduct = function () {
        ProductIdEdit = null;
        document.getElementById("ProductName").value = "";
        document.getElementById("ProductPrice").value = 0;
        showFormEdit();

    };

    $scope.editProduct = function (productId) {
        $http.get(constPatchProduct + "/products/" + productId)
            .then(function (response) {
                ProductIdEdit = response.data.id;
                document.getElementById("ProductName").value = response.data.title;
                document.getElementById("ProductPrice").value = response.data.price;
                showFormEdit();
            });
    };
    $scope.deleteProduct = function (productId) {
        $http.delete(constPatchProduct + "/products/" + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };
    $scope.saveProduct = function () {
        console.log($scope.Product);
        $http({
            url: constPatchProduct + "/products",
            method: "POST",
            params: {
                id: ProductIdEdit ? ProductIdEdit : null,
                title: document.getElementById("ProductName").value,
                price: document.getElementById("ProductPrice").value
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    };

    $scope.loadBasket = function () {
        $http.get(constPatchBasket)
            .then(function (response) {
                $scope.Basket = response.data;
                showProducts();
            });

    };
    $scope.addProductToBasket = function (productId) {
        $http({
            url: constPatchBasket + "/add",
            method: "GET",
            params: {
                productId: productId
            }
        }).then(function (response) {
            $scope.loadBasket();
        });

    };
    $scope.delProductToBasket = function (productId) {
        console.log(productId);
        $http({
            url: constPatchBasket + "/delete",
            method: "GET",
            params: {
                productId: productId
            }
        }).then(function (response) {
            $scope.loadBasket();
        });

    };
    $scope.clearBasket = function () {
        $http({
            url: constPatchBasket + "/clear",
            method: "GET"
        }).then(function (response) {
            $scope.loadBasket();
        });
    };
    $scope.tryToAuth = function () {
        $http.post(constPatchAuth + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.authUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
                $scope.loadProducts();
                $scope.loadBasket();
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.authUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.authUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.saveOrder = function () {
        $http.get(constPatchOrder+ "/orders/save")
            .then(function (response) {
                $scope.Basket = null;
                alert("Заказ оформлен")
            });

    };

    $scope.loadProducts();
    $scope.loadBasket();
})