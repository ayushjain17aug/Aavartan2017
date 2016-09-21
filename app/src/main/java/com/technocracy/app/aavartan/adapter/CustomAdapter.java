package com.technocracy.app.aavartan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.activity.intentactivity.ViewPDF;
import com.technocracy.app.aavartan.helper.App;

/**
 * Created by nsn on 8/25/2015.
 */
public class CustomAdapter extends BaseAdapter {

    private final int[] movImgid;

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Context c, String[] prgmNameList,int movImgs[]) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=c;
        movImgid=movImgs;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        KenBurnsView kbv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.problemlist, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.kbv=(KenBurnsView)rowView.findViewById(R.id.megaImage);
        holder.tv.setText(result[position]);
        holder.kbv.setImageResource(movImgid[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "You Clicked " + result[position]+"  "+position, Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub
                 switch (position) {
                    case 0:
                        App.LinkPDF = App.ArchiPDF;
                        Intent i1 = new Intent(context, ViewPDF.class);
                        context.startActivity(i1);
                         break;
                    case 1:
                        App.LinkPDF = App.BioMedPDF;
                        Intent i2 = new Intent(context, ViewPDF.class);
                        context.startActivity(i2);
                        break;
                    case 2:
                        App.LinkPDF = App.BioTechPDF;
                        Intent i3 = new Intent(context, ViewPDF.class);
                        context.startActivity(i3);
                        break;
                    case 3:
                        App.LinkPDF = App.ChemPDF;
                        Intent i4 = new Intent(context, ViewPDF.class);
                        context.startActivity(i4);
                        break;
                    case 4:
                        App.LinkPDF = App.CivilPDF;
                        Intent i5 = new Intent(context, ViewPDF.class);
                        context.startActivity(i5);
                        break;
                    case 5:
                        App.LinkPDF = App.CSEPDF;
                        Intent i6 = new Intent(context, ViewPDF.class);
                        context.startActivity(i6);
                        break;
                    case 6:
                        App.LinkPDF = App.ElecPDF;
                        Intent i7 = new Intent(context, ViewPDF.class);
                        context.startActivity(i7);
                        break;
                    case 7:
                        App.LinkPDF = App.ElexPDF;
                        Intent i8 = new Intent(context, ViewPDF.class);
                        context.startActivity(i8);
                        break;
                    case 8:
                        App.LinkPDF = App.ITPDF;
                        Intent i9 = new Intent(context, ViewPDF.class);
                        context.startActivity(i9);
                        break;
                    case 9:
                        App.LinkPDF = App.MechPDF;
                        Intent i10 = new Intent(context, ViewPDF.class);
                        context.startActivity(i10);
                        break;
                    case 10:
                        App.LinkPDF = App.MetaPDF;
                        Intent i11 = new Intent(context, ViewPDF.class);
                        context.startActivity(i11);
                        break;
                    case 11:
                        App.LinkPDF = App.MiningPDF;
                        Intent i12 = new Intent(context, ViewPDF.class);
                        context.startActivity(i12);
                        break;
                    case 12:
                        App.LinkPDF = App.MCAPDF;
                        Intent i13 = new Intent(context, ViewPDF.class);
                        context.startActivity(i13);
                        break;
                    case 13:
                        App.LinkPDF = App.EcellPDF;
                        Intent i14 = new Intent(context, ViewPDF.class);
                        context.startActivity(i14);
                        break;
                    case 14:
                        App.LinkPDF = App.GoGreenPDF;
                        Intent i15 = new Intent(context, ViewPDF.class);
                        context.startActivity(i15);
                        break;
                    default:
                        break;
                }
            }
        });
        return rowView;
    }

}
