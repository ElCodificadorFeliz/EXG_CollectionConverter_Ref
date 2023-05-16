package collectionConverter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import stuffBeginnersDontHaveToUnderstand.Version;


/**
 * Exercise "CollectionConverter" - see task.
 * 
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class Converter {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_002___2023_05_10__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung.
    
    
    
    
    
    //...
    
    /**
     * ...
     * 
     * @param  exteriorMap  nested map that contains an interior map as datavalue
     * @return  flattened nested map as list
     */
    public List<Object> toList( final Map<Object,Map<Object,Object>> exteriorMap ){
      //assert null!=exteriorMap : "Illegal Argument : null is NOT supported";
        
        final List<Object> list = new ArrayList<>();
        final Set<Entry<Object,Map<Object,Object>>> exteriorEntrySet = exteriorMap.entrySet();
        for( final Entry<Object,Map<Object,Object>> exteriorEntry : exteriorEntrySet ){
            final Map<Object,Object> interiorMap = exteriorEntry.getValue();
            final Set<Entry<Object,Object>> interiorEntrySet = interiorMap.entrySet();
            for( final Entry<Object,Object> interiorEntry : interiorEntrySet ){
                list.add( exteriorEntry.getKey() );
                list.add( interiorEntry.getKey() );
                list.add( interiorEntry.getValue() );
            }//for
        }//for
        return list;
    }//method()
    
    
    
    /**
     * ...
     * 
     * @param list  list containing flattened nested map
     * @return  nested map
     */
    public Map<Object,Map<Object,Object>> toNestedMap( final List<Object> list ){
      //assert null!=list : "Illegal Argument : null is NOT supported";
      //assert 0==list.size()%3 : "Illegal Argument : List requires multiple of three elements that it can be converted to nested map";
        
        final Map<Object,Map<Object,Object>> exteriorMap = new HashMap<Object,Map<Object,Object>>();
        int i=0;
        while( i<list.size() /*-2*/ ){                                          // "-2" NOT needed
            final Object exteriorKey = list.get( i++ );
            final Object interiorKey = list.get( i++ );
            final Object interiorValue = list.get( i++ );
          //assert null!=exteriorKey && null!=interiorKey && null!=interiorValue : "Illegal Argument :null is NOT supported as list element";
            Map<Object,Object> interiorMap = exteriorMap.get( exteriorKey );
            if( null==interiorMap ){
                interiorMap = new HashMap<Object,Object>();
                exteriorMap.put( exteriorKey, interiorMap );
            }//if
            interiorMap.put( interiorKey, interiorValue );
        }//for
        return exteriorMap;
    }//method()
    
}//class
