angular.module('app', ['ngStorage']).controller("indexController", function ($scope, $http, $localStorage) {
    const constGlobalPatch = 'http://localhost:8180/app';
    const constPatch = 'http://localhost:8180/app/v1';

    var showProducts = function () {
        document.getElementById("ProductList").style.display = "block";
        document.getElementById("FormEdit").style.display = "none";
    };
    var showFormEdit = function () {
        document.getElementById("ProductList").style.display = "none";
        document.getElementById("FormEdit").style.display = "block";
    };
    if ($localStorage.authUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.authUser.token;
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
            url: constPatch + "/products",
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
        $http.get(constPatch + "/products/" + productId)
            .then(function (response) {
                ProductIdEdit = response.data.id;
                document.getElementById("ProductName").value = response.data.title;
                document.getElementById("ProductPrice").value = response.data.price;
                showFormEdit();
            });
    };
    $scope.deleteProduct = function (productId) {
        $http.delete(constPatch + "/products/" + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };
    $scope.saveProduct = function () {
        console.log($scope.Product);
        $http({
            url: constPatch + "/products",
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
    var basket = "1";
    $scope.loadBasket = function () {
        $http.get(constPatch + "/baskets/" + basket)
            .then(function (response) {
                $scope.Basket = response.data;
                showProducts();
            });

    };
    $scope.addProductToBasket = function (productId) {
        $http({
            url: constPatch + "/baskets/add",
            method: "GET",
            params: {
                basketId: basket,
                productId: productId
            }
        }).then(function (response) {
            $scope.loadBasket();
        });

    };
    $scope.delProductToBasket = function (productId) {
        $http({
            url: constPatch + "/baskets/delete/" + basket,
            method: "GET",
            params: {
                productId: productId
            }
        }).then(function (response) {
            $scope.loadBasket();
        });

    };
    $scope.tryToAuth = function () {
        $http.post(constGlobalPatch + '/auth', $scope.user)
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


    $scope.loadProducts();
    $scope.loadBasket();
})