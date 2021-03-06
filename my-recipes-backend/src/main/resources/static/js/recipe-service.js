var recipeService = new function() {
	
	this.recipeUrl = 'recipe';
	
	this.findRecipeByFilter = function(word, filter) {
		var data = {word: word, filter: filter};
		return $.get(this.recipeUrl, data).promise();
	}
	
	this.loadAllRecipes = function() {
		$('#table-recipes').DataTable( {
	        'ajax': {
	        	'dataType': 'json',
	            'contentType': 'application/json; charset=utf-8',
	        	'url': 'recipe',
	            'dataSrc': function (json) {
	                return $.parseJSON(JSON.stringify(json));
	             }
	        },
	        "columns": [
	            { "data": "name" },
	            { "data": "url" }
	        ]
	    } );
	}
	
	this.findRecipe = function(id, callback) {
		$.get(this.recipeUrl +'/' + id)
		.done(function(recipe){
			callback(recipe);
		})
		.fail(function(e){
			displayMsg('Error ' + e);
		});
	}
	
	this.saveRecipe = function(recipe) {
		$.ajax({
			url: this.recipeUrl,
			type: 'POST',
			data: JSON.stringify(recipe),
			contentType:"application/json; charset=utf-8"
		})
		.done(function(recipe){
			displayMsg('Recipe saved');
		})
		.fail(function(e){
			displayMsg('Error ' + e);
		});
	}
};