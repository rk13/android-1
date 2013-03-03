package name.aleksejstruhans.sweertodolist;


import java.util.ArrayList;
import java.util.List;

import name.aleksejstruhans.sweertodolist.model.Category;
import name.aleksejstruhans.sweertodolist.model.CategoryList;
import name.aleksejstruhans.sweertodolist.model.TodoList;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

	Category selectedCategory;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EntityHolder.setCategoryList(CategoryList.createInitialList()); 
        EntityHolder.setTodoList(new TodoList());
        
        initSpinner();

        setContentView(R.layout.activity_main);
    }

	private void initSpinner() {
		List<CategoryWrapper> categoryWrapperList = new ArrayList<CategoryWrapper>(); 
		CategoryList categoryList = EntityHolder.getCategoryList(); 
		for (Category category : categoryList) { 
			categoryWrapperList.add(new CategoryWrapper(category));
		}
		ArrayAdapter<CategoryWrapper> adapter = new ArrayAdapter<CategoryWrapper>(this,
				android.R.layout.simple_spinner_item, categoryWrapperList);
		
		Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
		
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				@SuppressWarnings("unchecked")
				CategoryWrapper categoryWrapper = (CategoryWrapper) ((ArrayAdapter<CategoryWrapper>) parent.getAdapter()).getItem(position);
				selectedCategory = categoryWrapper.getCategory(); 
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				selectedCategory = null;
			} 
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
