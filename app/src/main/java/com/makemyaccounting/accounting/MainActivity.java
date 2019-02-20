package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.makemyaccounting.dashboard.R;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    SharedPreferences lt;
    final String MYPREFER = "login";

    private Toolbar mToolbar;
    AlertDialog alertDialog1;
    String[] values = {"PRODUCT", "SERVICES"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setTitle("Dashboard");
        DashboardFragment dashboardFragment = new DashboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, dashboardFragment).commit();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        int id = menuItem.getItemId();
        int size = navigationView.getMenu().size();
        int count=1;


        if (menuItem.isChecked()){

            DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        if (id== R.id.nav_dash) {

            Intent add = new Intent(getApplicationContext(),AddCustomerActivity.class);
            startActivity(add);



            //setTitle("Dashboard");
            //DashboardFragment dashboardFragment = new DashboardFragment();
            //FragmentManager fragmentManager = getSupportFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.main_frame, dashboardFragment).commit();
        }

        if (id== R.id.nav_sale) {

            CreateAlertDialogWithRadioButtonGroup();
                       // setTitle("Sale");
            //SaleFragment saleFragment = new SaleFragment();
            //FragmentManager fragmentManager = getSupportFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.main_frame, saleFragment).commit();
        }
        if (id== R.id.nav_purchase) {

            setTitle("Purchase");
            Intent pi=new Intent(getApplicationContext(),Purchase.class);
            startActivity(pi);
                    }
        if (id== R.id.nav_saleactiv) {

            setTitle("Sale");
            Intent pi=new Intent(getApplicationContext(),SaleActivity.class);
            startActivity(pi);
        }

        if (id== R.id.nav_debit_note) {

            setTitle("Debit Note");
            Intent pi=new Intent(getApplicationContext(),Debitnote.class);
            startActivity(pi);
        }

        if (id== R.id.nav_credit_note) {

            setTitle("Debit Note");
            Intent pi=new Intent(getApplicationContext(),Creditnote.class);
            startActivity(pi);
        }

        DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }

        if(item.getItemId()==R.id.nav_balance_sheet)
        {
            Toast.makeText(this, "Clicked Balance Sheet", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_right_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }



    public void CreateAlertDialogWithRadioButtonGroup(){
        lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = lt.edit();


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Select Your Choice");

        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:

                        editor.putString("type","P");
                        editor.commit();
                        Intent intent=new Intent(getApplicationContext(),Add_item.class);
                        startActivity(intent);
                        break;
                    case 1:

                        editor.putString("type","S");
                        editor.commit();
                        Intent intent1=new Intent(getApplicationContext(),Add_item.class);
                        startActivity(intent1);
                        break;

                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();

    }


}
