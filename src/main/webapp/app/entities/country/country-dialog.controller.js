(function() {
    'use strict';

    angular
        .module('pocApp')
        .controller('CountryDialogController', CountryDialogController);

    CountryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Country', 'Person', 'Company'];

    function CountryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Country, Person, Company) {
        var vm = this;

        vm.country = entity;
        vm.clear = clear;
        vm.save = save;
        vm.people = Person.query();
        vm.companies = Company.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.country.id !== null) {
                Country.update(vm.country, onSaveSuccess, onSaveError);
            } else {
                Country.save(vm.country, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('pocApp:countryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
