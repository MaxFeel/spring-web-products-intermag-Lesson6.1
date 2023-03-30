angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1';
    const contextPathCart = 'http://localhost:5555/cart/api/v1';

    if ($localStorage.springWebUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };


    $scope.loadCart = function () {
            $http.get(contextPathCart + '/cart').then(function (response){
                $scope.cart = response.data;
            });
    };

    $scope.deleteAllProducts = function () {
                $http.get(contextPathCart + '/cart/deleteAll').then(function (response){
                    $scope.loadCart();
                });
     };

    $scope.addToCart = function(productId){
        $http.get(contextPathCart + '/cart/add/' + productId).then(function (response){
             $scope.loadCart();
        });
    };

    $scope.deleteFromCart = function (productId) {
                $http.get(contextPathCart + '/cart/delete/' + productId).then(function (response) {
                        $scope.loadCart();
                    });
    };
    /*
    $scope.saveOrder = function(){
            console.log($scope.cart.items);
            $http.post(contextPath + '/order', $scope.cart.items).then(function (response){
                 $scope.deleteAllProducts();
            });
    };
    */

    $scope.createOrder = function(){
                console.log($scope.cart.items);
                $http.post(contextPath + '/order').then(function (response){
                    alert('Заказ оформлен');
                    $scope.loadCart()
                     //$scope.deleteAllProducts();
                });
        };



    $scope.tryToAuth = function () {
            $http.post('http://localhost:5555/auth/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
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
            delete $localStorage.springWebUser;
            $http.defaults.headers.common.Authorization = '';
        };

        $rootScope.isUserLoggedIn = function () {
            if ($localStorage.springWebUser) {
                return true;
            } else {
                return false;
            }
        };
        /*
        $scope.showCurrentUserInfo = function () {
            $http.get('http://localhost:5555/auth/api/v1/profile')
                .then(function successCallback(response) {
                    alert('MY NAME IS: ' + response.data.username);
                }, function errorCallback(response) {
                    alert('UNAUTHORIZED');
                });
        };
        */

        $scope.changeCount = function (productId, delta) {
                $http({
                    url: contextPathCart + '/cart/change_count',
                    method: 'GET',
                    params: {
                        productId: productId,
                        delta: delta
                    }
                }).then(function (response) {
                    $scope.loadCart();
                });
        };

    $scope.loadProducts();
    //$scope.loadCarts();
    $scope.loadCart();
});