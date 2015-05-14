/**
*  Module
*
* Description
*/
var notifWeb = angular.module('notifWeb', []);

notifWeb.factory('SendMessageService', ['$http', function($http){
	var sendMessageService = {};

	sendMessageService.checkNames = function() {		
		return $http.get("email.json");
	};

	sendMessageService.sendMessage = function(message) {
		$http.put("message.json", message);
	}

	return sendMessageService;
}]);

notifWeb.controller('sendMessageCtrl', ['$scope', 'SendMessageService',function($scope, SendMessageService){
	$scope.message={};
	$scope.message.contenttype="text";
	$scope.message.text=true;
	$scope.contenttypes=[
		{label:"File", value:"file"},
		{label:"Text", value:"text"}];
	$scope.sendMessage = function(){
		console.log($scope.message);
		SendMessageService.sendMessage($scope.message);
	};
	$scope.$watch('message.contenttype', function(newvalue, oldvalue){
		if(newvalue) {
			if(newvalue=="text") {
				$scope.message.text=true;
				$scope.message.file=false;
			} else if(newvalue=="file"){
				$scope.message.text=false;
				$scope.message.file=true;
			}	
		}
	});
}]);

notifWeb.directive('emailTo', ['$http', 'SendMessageService', function($http, sendMessageService){
	// Runs during compile
	return {
		restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
		template: '<input id="to" type="text" class="form-control" placeholder="Enter Receipents" ng-model="message.to"></input>',		
		link: function($scope, element, attr, controller) {
			
		    var split = function ( val ) {
		      return val.split( /;\s*/ );
		    }
		    var extractLast = function ( term ) {
		      return split( term ).pop();
		    }
		 
		    $(element).find("input#to")
		      // don't navigate away from the field on tab when selecting an item
		      .bind( "keydown", function( event ) {
		        if ( event.keyCode === $.ui.keyCode.TAB &&
		            $( this ).autocomplete( "instance" ).menu.active ) {
		          event.preventDefault();
		        }
		      })
		      .autocomplete({
		        source: function( request, response ) {
		          /*$.getJSON( "email.json", {
		            term: extractLast( request.term )
		          }, response );*/
		      	sendMessageService.checkNames().success(function(serverNames){
		      		response(serverNames);	
		      	});
		          
		        },
		        search: function() {
		          // custom minLength
		          var term = extractLast( this.value );
		          if ( term.length < 4 ) {
		            return false;
		          }
		        },
		        focus: function() {
		          // prevent value inserted on focus
		          return false;
		        },
		        select: function( event, ui ) {
		          var terms = split( this.value );
		          // remove the current input
		          terms.pop();
		          // add the selected item
		          terms.push( ui.item.value );
		          // add placeholder to get the comma-and-space at the end
		          terms.push( "" );
		          this.value = terms.join( "; " );
		          return false;
		        }
		      });
		  }
		}
}]);