var recipeController = new function(){
	
	this.recipesMap = {};
	this.recipeId = '';
	
	this.initApp = function(){
		recipeService.loadAllRecipes();
		
		$('.typeahead').typeahead({	
			source: function (word, process) {
				var recipes = [];
				var response = [];
				
				var filter = $("#recipeFilter").val();
				response = recipeService.findRecipeByFilter(word, filter);
				
				response.then(function(data){
					$.each(data, function (i, recipe) {
						recipeController.recipesMap[recipe.name] = recipe;
						recipes.push(recipe.name);
					});
					
					process(recipes);
				});
				
			},
			updater: function (item) {
				recipeController.findRecipe(recipeController.recipesMap[item].id);
				recipeController.recipeId = recipeController.recipesMap[item].id;
				return item;
			}
		});
		
		$("#saveButton").click(function(){
			recipeController.saveRecipe();
		});
		
		$("#newButton").click(function(){
			recipeController.newRecipe();
		});
	}
	
	this.findRecipe = function(id) {
		recipeService.findRecipe(id,this.displayRecipe);
	}
	
	this.displayRecipe = function(recipe){
		$("#recipeName").val(recipe.name);
		$("#recipeUrl").val(recipe.url);
		$("#recipeDescription").val(recipe.description);
	}
	
	this.saveRecipe = function(){
		var recipe = {id: recipeController.recipeId, name: $("#recipeName").val(), url: $("#recipeUrl").val(), description: $("#recipeDescription").val()};
		recipeService.saveRecipe(recipe);
	}
	
	this.newRecipe = function(){
		recipeController.recipeId = '';
		$("#recipeName").val('');
		$("#recipeUrl").val('');
		$("#recipeDescription").val('');
	}
};
	
$(function() {
	 recipeController.initApp();
});