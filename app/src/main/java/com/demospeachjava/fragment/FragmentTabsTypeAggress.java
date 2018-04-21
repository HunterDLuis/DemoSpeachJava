package com.demospeachjava.fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.demospeachjava.R;
import com.demospeachjava.utils.Tools;

public class FragmentTabsTypeAggress extends Fragment {

    private static final int MAX_STEP = 15;

    private ViewPager viewPager;
    private Button btnNext;
    private View root_view;
    private MyViewPagerAdapter myViewPagerAdapter;
    private String about_aggression[] = {
            "Fisica",
            "Feminicida",
            "Psicologica",
            "Mediatica",
            "Social",
            "Dignidad",
            "Sexual",
            "Reproduccion",
            "Salud",
            "Economica",
            "Laboral",
            "Educacion",
            "Politico",
            "Institucional",
            "Familia"
    };
    private String about_description_aggression[] = {
            "Cualquier acto de violencia que causa lesión o daño corporal por los golpes, ultrajes, a través del empleo de la fuerza física o cualquier otro medio.",
            "Es la extrema violencia, golpes, patadas y el empleo de armas que causan la muerte de la mujer por el hecho de serlo.",
            "Es el conjunto de acciones sistemáticas de desvalorización, intimidación, daño emocional que causa la baja autoestima.",
            "Es la difusión de imágenes y mensajes que promueven la sumisión y explotación de mujeres. Las humillan y difaman públicamente.",
            "Son las críticas indirectas, juicios y descalificación de la mujer o control, aparentemente para protegerla, como aislarla del entorno.",
            "Es toda expresión verbal o escrita de ofensa, insulto, difamación, calumnia, amenaza que desacredita, desvaloriza y afecta la dignidad.",
            "Toda acción u omisión que restrinja el ejercicio de los derechos de las mujeres, tanto en el acto sexual como en toda forma de contacto.",
            "Es la acción que impide, limita o vulnera el derecho a la información y a elegir anticonceptivos, el derecho a decidir el número de hijos que quiere tener.",
            "Toda acción discriminatoria, deshumanizada que niega o restringe el acceso a una atención con calidad y calidez por parte del personal de salud.",
            "Todo lo que al afectar los bienes propios o gananciales de la mujer ocasiona una disminución de sus ingresos.",
            "Es la humillación, discriminación, amenaza  o intimidación en el ámbito del trabajo ejercido por cualquier persona superior, igual o inferior en jerarquía.",
            "Cualquier tipo de agresión sexual, psicológica, física cometida contra la mujer en el sistema educativo regular, alternativo, especial y superior.",
            "Todo acto de acoso o violencia contra una mujer candidata, electa, designada o en el ejercicio de la función político pública.",
            "Todo lo que implique una acción discriminatoria, prejuiciosa, humillante y deshumanizada que retarde el acceso      y atención requerido.",
            "Toda agresión física, psicológica, económica u otros contra la mujer dentro del ámbito familiar, el esposo, padre, hermano, parientes."
    };
    private int about_images_aggression[] = {
            R.drawable.fisica,
            R.drawable.img_wizard_1,
            R.drawable.psicologica,
            R.drawable.img_wizard_2,
            R.drawable.social,
            R.drawable.dignidad,
            R.drawable.sexual,
            R.drawable.img_wizard_3,
            R.drawable.img_wizard_4,
            R.drawable.economica,
            R.drawable.laboral,
            R.drawable.img_no_chat,
            R.drawable.img_no_internet_satellite,
            R.drawable.img_no_feed,
            R.drawable.img_no_friend

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        root_view = inflater.inflate(R.layout.fragment_card_aggression, container, false);

        viewPager = (ViewPager) root_view.findViewById(R.id.view_pager);
        btnNext = (Button) root_view.findViewById(R.id.btn_next);

        // adding bottom dots
        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        Tools.setSystemBarColor(getActivity(), R.color.colorPrimaryDark);
        return root_view;
    }

    public FragmentTabsTypeAggress() {
    }

    public static FragmentTabsTypeAggress newInstance() {
        FragmentTabsTypeAggress fragment = new FragmentTabsTypeAggress();
        return fragment;
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) root_view.findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.mdtp_white), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.light_green_600), PorterDuff.Mode.SRC_IN);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private Button btnNext;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_card_aggression, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(about_aggression[position]);
            ((TextView) view.findViewById(R.id.description)).setText(about_description_aggression[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_aggression[position]);

            btnNext = (Button) view.findViewById(R.id.btn_next);

            if (position == about_aggression.length - 1) {
                btnNext.setText("Obtener mas informacion.");
            } else {
                btnNext.setText("Proximo.");
            }


            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = viewPager.getCurrentItem() + 1;
                    if (current < MAX_STEP) {
                        // move to next screen
                        viewPager.setCurrentItem(current);
                    } else {
                        viewPager.setCurrentItem(position);
                    }
                }
            });

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return about_aggression.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
