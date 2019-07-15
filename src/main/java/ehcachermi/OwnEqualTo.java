package ehcachermi;

import net.sf.ehcache.Element;
import net.sf.ehcache.search.SearchException;
import net.sf.ehcache.search.attribute.AttributeExtractor;
import net.sf.ehcache.search.attribute.AttributeType;
import net.sf.ehcache.search.expression.BaseCriteria;

import java.util.Map;

/**
 * 
 * @author liuyan
 *bigmemory查询方法eq对String不区分大小写(对大小写不敏感)
 *该类为了查询方法实现对String大小写敏感
 */
public class OwnEqualTo extends BaseCriteria
{
  private final Object value;
  private final String attributeName;
  private final AttributeType type;

  public OwnEqualTo(String attributeName, Object value)
  {
    if ((value == null) || (attributeName == null)) {
      throw new NullPointerException();
    }

    this.attributeName = attributeName;
    this.value = value;

    this.type = AttributeType.typeFor(attributeName, value);
  }

  public Object getValue()
  {
    return this.value;
  }

  public String getAttributeName()
  {
    return this.attributeName;
  }

  public AttributeType getType()
  {
    return this.type;
  }

  public boolean execute(Element e, Map<String, AttributeExtractor> attributeExtractors)
  {
    Object attributeValue = getExtractor(getAttributeName(), attributeExtractors).attributeFor(e, getAttributeName());
    if (attributeValue == null) {
      return false;
    }
    AttributeType attrType = AttributeType.typeFor(getAttributeName(), attributeValue);
    if (!getType().equals(attrType)) {
      throw new SearchException("Expecting attribute of type " + getType().name() + " but was " + attrType.name());
    }

    if (getType().equals(AttributeType.STRING)) {
    //return ((String)this.value).equalsIgnoreCase((String)attributeValue);
      return ((String)this.value).equals((String)attributeValue);
    }
    return this.value.equals(attributeValue);
  }
}