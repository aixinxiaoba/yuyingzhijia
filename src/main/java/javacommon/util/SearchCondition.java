package javacommon.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SimpleExpression;

@SuppressWarnings("unchecked")
public class SearchCondition
{
    private List lstCondition;

    public SearchCondition()
    {
    }

    public SearchCondition(SimpleExpression objConditionOne)
    {
        lstCondition = new ArrayList<SimpleExpression>();

        lstCondition.add(objConditionOne);
    }

    public SearchCondition(SimpleExpression objConditionOne, SimpleExpression objConditionTwo)
    {
        lstCondition = new ArrayList<SimpleExpression>();

        lstCondition.add(objConditionOne);
        lstCondition.add(objConditionTwo);
    }

    public SearchCondition(SimpleExpression objConditionOne, SimpleExpression objConditionTwo, SimpleExpression objConditionThree)
    {
        lstCondition = new ArrayList<SimpleExpression>();

        lstCondition.add(objConditionOne);
        lstCondition.add(objConditionTwo);
        lstCondition.add(objConditionThree);
    }

    public SearchCondition(SimpleExpression objConditionOne, SimpleExpression objConditionTwo, SimpleExpression objConditionThree, SimpleExpression objConditionFour)
    {
        lstCondition = new ArrayList<SimpleExpression>();

        lstCondition.add(objConditionOne);
        lstCondition.add(objConditionTwo);
        lstCondition.add(objConditionThree);
        lstCondition.add(objConditionFour);
    }

    public SearchCondition(SimpleExpression objConditionOne, SimpleExpression objConditionTwo, SimpleExpression objConditionThree, SimpleExpression objConditionFour, SimpleExpression objConditionFive)
    {
        lstCondition = new ArrayList<SimpleExpression>();

        lstCondition.add(objConditionOne);
        lstCondition.add(objConditionTwo);
        lstCondition.add(objConditionThree);
        lstCondition.add(objConditionFour);
        lstCondition.add(objConditionFive);
    }

    public SearchCondition(Criterion objConditionOne, SimpleExpression objConditionTwo, SimpleExpression objConditionThree, SimpleExpression objConditionFour, SimpleExpression objConditionFive)
    {
        lstCondition = new ArrayList();

        if (objConditionOne != null)
        {
            lstCondition.add(objConditionOne);
        }
        if (objConditionTwo != null)
        {
            lstCondition.add(objConditionTwo);
        }
        if (objConditionThree != null)
        {
            lstCondition.add(objConditionThree);
        }
        if (objConditionFour != null)
        {
            lstCondition.add(objConditionFour);
        }
        if (objConditionFive != null)
        {
            lstCondition.add(objConditionFive);
        }
    }
    
    public SearchCondition(Criterion objConditionOne, SimpleExpression objConditionTwo)
    {
        lstCondition = new ArrayList();

        if (objConditionOne != null)
        {
            lstCondition.add(objConditionOne);
        }
        if (objConditionTwo != null)
        {
            lstCondition.add(objConditionTwo);
        }
    }
    
    public SearchCondition(Criterion objConditionOne, SimpleExpression objConditionTwo, SimpleExpression objConditionThree)
    {
        lstCondition = new ArrayList();

        if (objConditionOne != null)
        {
            lstCondition.add(objConditionOne);
        }
        if (objConditionTwo != null)
        {
            lstCondition.add(objConditionTwo);
        }
        if (objConditionThree != null)
        {
            lstCondition.add(objConditionThree);
        }
    }

    public List getLstCondition()
    {
        return lstCondition;
    }

    public void setLstCondition(List lstCondition)
    {
        this.lstCondition = lstCondition;
    }
}
