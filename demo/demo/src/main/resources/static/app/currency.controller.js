/**
 * Created by Krzysztof Sulima on 17.04.2017.
 */

(function () {
    'use strict';
    angular
        .module('app')
        .controller('currencyController', CurrencyController);

    CurrencyController.$inject = [];

    function CurrencyController(){
        var vm = this;
    }

})();
