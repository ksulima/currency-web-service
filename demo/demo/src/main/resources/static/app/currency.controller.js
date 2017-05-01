/**
 * Created by Krzysztof Sulima on 17.04.2017.
 */

(function () {
    'use strict';
    angular
        .module('app')
        .controller('CurrencyController', CurrencyController);

    CurrencyController.$inject = ['$http'];

    function CurrencyController($http){
        var vm = this;

        vm.currencies = [];
        vm.getLatest = getLatest;

        function getLatest(){
            var url = "/api/get/latest";
            var currencyPromise = $http.get(url);
            currencyPromise.then(function(response) {
                vm.currencies = response.data;
            });
        }



    }

})();
