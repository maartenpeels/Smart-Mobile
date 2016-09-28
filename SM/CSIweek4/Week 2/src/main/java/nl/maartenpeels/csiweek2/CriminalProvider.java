package nl.maartenpeels.csiweek2;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maarten Peels on 9/16/2016.
 */
public class CriminalProvider {
    private Resources resources;
    private List<Criminal> criminals;

    public CriminalProvider(Resources r)
    {
        this.resources = r;
        criminals = getCriminalsLocal();
    }

    private List<Criminal> getCriminalsLocal()
    {
        List<Criminal> criminals = new ArrayList<>();

        TypedArray names = resources.obtainTypedArray(R.array.criminalName);
        TypedArray genders = resources.obtainTypedArray(R.array.criminalGender);
        TypedArray ages = resources.obtainTypedArray(R.array.criminalAge);
        TypedArray bounties = resources.obtainTypedArray(R.array.criminalBounty);
        TypedArray details = resources.obtainTypedArray(R.array.criminalDetails);
        TypedArray images = resources.obtainTypedArray(R.array.criminalImage);

        for(int i = 0; i < names.length(); i++)
        {
            criminals.add(new Criminal(names.getString(i), genders.getString(i), ages.getInt(i, 0),
                    bounties.getString(i), details.getString(i), images.getDrawable(i)));
        }

        names.recycle();
        genders.recycle();
        ages.recycle();
        bounties.recycle();
        details.recycle();
        images.recycle();

        return criminals;
    }

    public List<Criminal> getCriminals()
    {
        return criminals;
    }

    public Criminal getCriminal(int index)
    {
        return criminals.get(index);
    }
}
