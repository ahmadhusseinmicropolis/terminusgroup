(function () {
    "use strict";

    //getting the module
    angular.module("my-app").service("userService", userService);

    function userService($http) {

        this.getUsers = function () {
            return $http.get('/api/users');
        }

        this.addUser = function (user) {
            return $http.post('/api/users', user);
        }

        this.editUser = function (newUser) {
            return $http.put('/api/users', newUser);
        }

        this.deleteUser = function (id) {
            return $http.delete('/api/users/' + id);
        }
    }

})();