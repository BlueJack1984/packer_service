package com.packer.commons.sms.util;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil {
    public CollectionUtil() {
    }

    public static void updateLinkedHashMap(LinkedHashMap mapping, String oldKey, String newKey, Object updatedObject) {
        Map tempFieldMapping = new LinkedHashMap(mapping);
        mapping.clear();
        Iterator it = tempFieldMapping.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            Object obj = tempFieldMapping.get(key);
            if (!oldKey.equals(key)) {
                mapping.put(key, obj);
            } else {
                mapping.put(newKey, updatedObject);
            }
        }

        tempFieldMapping.clear();
    }

    public static void moveLinkedHashMap(LinkedHashMap mapping, String key, int step) {
        if (mapping.containsKey(key)) {
            Iterator keysIt = mapping.keySet().iterator();
            int keyIndex = -1;

            while(keysIt.hasNext()) {
                ++keyIndex;
                String currentKey = (String)keysIt.next();
                if (currentKey.equals(key)) {
                    break;
                }
            }

            Object movedObject = mapping.remove(key);
            int newKeyIndex = keyIndex + step;
            if (newKeyIndex < 0) {
                newKeyIndex = 0;
            } else if (newKeyIndex >= mapping.size()) {
                newKeyIndex = mapping.size();
            }

            Map tempFieldMapping = new LinkedHashMap(mapping);
            mapping.clear();
            int index = 0;

            for(Iterator it = tempFieldMapping.keySet().iterator(); it.hasNext(); ++index) {
                if (index == newKeyIndex) {
                    mapping.put(key, movedObject);
                }

                String currentKey = (String)it.next();
                Object currentObj = tempFieldMapping.get(currentKey);
                mapping.put(currentKey, currentObj);
            }

            if (index == newKeyIndex) {
                mapping.put(key, movedObject);
            }

            tempFieldMapping.clear();
        }
    }

    public static boolean isFirstKey(Map mapping, Object key) {
        if (!mapping.containsKey(key)) {
            return false;
        } else {
            Set keySet = mapping.keySet();
            return keySet.iterator().next().equals(key);
        }
    }

    public static boolean isLastKey(Map mapping, Object key) {
        if (!mapping.containsKey(key)) {
            return false;
        } else {
            Set keySet = mapping.keySet();
            Object lastKey = null;

            for(Iterator it = keySet.iterator(); it.hasNext(); lastKey = it.next()) {
            }

            return lastKey.equals(key);
        }
    }

    public static void moveList(List objs, Object movedObj, int step) {
        int index = objs.indexOf(movedObj);
        if (index != -1) {
            objs.remove(movedObj);
            int newIndex = index + step;
            if (newIndex < 0) {
                newIndex = 0;
            } else if (newIndex >= objs.size()) {
                newIndex = objs.size();
            }

            objs.add(newIndex, movedObj);
        }
    }

    public static boolean isFirstKey(List list, Object key) {
        if (list == null) {
            return false;
        } else {
            return !list.contains(key) ? false : list.iterator().next().equals(key);
        }
    }

    public static boolean isLastKey(List list, Object key) {
        if (list == null) {
            return false;
        } else if (!list.contains(key)) {
            return false;
        } else {
            Object lastKey = list.get(list.size() - 1);
            return lastKey.equals(key);
        }
    }

    public static void main(String[] args) {
        LinkedHashMap mapping = new LinkedHashMap();
        mapping.put("a", "a");
        mapping.put("b", "b");
        mapping.put("c", "c");
        mapping.put("d", "d");
        mapping.put("e", "e");
        mapping.put("f", "f");
        System.out.println(mapping);
        moveLinkedHashMap(mapping, "b", 1);
        System.out.println(mapping);
    }
}

