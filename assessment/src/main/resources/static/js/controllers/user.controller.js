(function () {

    "use strict";

    //getting the module
    angular.module("my-app")
        .controller("userController", userController);

    function userController(userService) {

        var vm = this;
        vm.users = [];
        vm.newUser = {};
        vm.errorMessage = "";
        vm.isBusy = true;
        userService.getUsers().then(function (response) {

            angular.copy(response.data, vm.users);
        }, function (error) {
            vm.errorMessage = "Failed to load data: " + error;
        }).finally(function () {
            vm.isBusy = false;
        });

        vm.addUser = function () {
            vm.isBusy = true;
            userService.addUser(vm.newUser).then(function (response) {
                vm.users.push(response.data);
                vm.newUser = {};
                vm.hideModal();
            }, function (error) {
                debugger
                vm.errorMessage = error.data;
            }).finally(function () {
                vm.isBusy = false;
            });


        }

        vm.editUser = function () {
            vm.isBusy = true;
            userService.editUser(vm.newUser).then(function (response) {
                vm.newUser = {};
                vm.hideModal();
            }, function (error) {
                debugger
                vm.errorMessage = error.data;
            }).finally(function () {
                vm.isBusy = false;
            });
            vm.showModal();
        }

        vm.saveUser = function () {
            if (vm.modalMode == "new") {
                vm.addUser();
            } else {
                vm.editUser();
            }
        }

        vm.deleteUser = function (id) {
            vm.isBusy = true;
            userService.deleteUser(id).then(function (response) {
                vm.users.splice(vm.users.findIndex(item => item.id == id), 1)
            }, function (error) {
                vm.errorMessage = error.data;
            }).finally(function () {
                vm.isBusy = false;
            });
        }

        vm.saveUser = function () {
            if (vm.modalMode == "new") {
                vm.addUser();
            } else {
                vm.editUser();
            }
        }

        vm.showModal = function () {
            vm.modalMode = "new";
            $('#userModal').modal('show');
        };

        vm.showEditModal = function (user) {
            vm.modalMode = "edit";
            vm.newUser = user;
            $('#userModal').modal('show');
        }
        vm.hideModal = function () {
            $('#userModal').modal('hide');
            vm.newUser = {};
        }
    }


})();

