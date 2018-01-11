# DynamicContainerLayout
Android Layout that can contain as many view as you want and switch bewteen them

## Demo

<img src="https://github.com/xcelder/DynamicContainerLayout/blob/master/files/insta.gif"/>

## How to use?

### XML
```xml
<com.xcelder.dynamiccontainerlayout.views.DynamicContainerLayout
        android:id="@+id/dyn_container"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent">
        <ViewStub
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout="@layout/layout1"/>
        <ViewStub
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout="@layout/layout2"/>
        <ViewStub
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout="@layout/layout3"/>
    </com.xcelder.dynamiccontainerlayout.views.DynamicContainerLayout>
```

### Code

```java
public class MainActivity extends AppCompatActivity {

    DynamicContainerLayout dynamicContainerLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    dynamicContainerLayout.inflateViewById(R.layout.layout1);
                    return true;
                case R.id.navigation_dashboard:
                    dynamicContainerLayout.inflateViewById(R.layout.layout2);
                    return true;
                case R.id.navigation_notifications:
                    dynamicContainerLayout.inflateViewById(R.layout.layout3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dynamicContainerLayout = findViewById(R.id.dyn_container);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
```
