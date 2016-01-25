/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.data.impl;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public abstract class JsonPersistence {

//    abstract public void save();
//    abstract public User load();
//
//    private transient String jsonFilePattern = "%s_%s.json";
//
//    private transient Gson gson = new GsonBuilder().
//            excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.FINAL).
//            setPrettyPrinting().
//            create();
//
//    private String buildFilename(Object obj) {
//        return buildFilename(obj, null);
//    }
//
//    private String buildFilename(Object obj, String unique) {
//        String className = "";
//        String uniqueName = unique;
//
//        if (obj instanceof User) {
//            className = obj.getClass().getSimpleName();
//
//            // use email username for filename if unique not provided
//            if (unique == null)
//                uniqueName = ((User) obj).getLogin().getEmail().split("@")[0];
//        }
//        else {
//            throw new RuntimeException("Invalid object type. Please check your testng code.");
//        }
//
//        return String.format(jsonFilePattern, className, uniqueName);
//    }
//
//    protected void write(Object obj) {
//        OutputStream out = null;
//        JsonWriter writer = null;
//
//        // generate filename from the object type
//        String filename = buildFilename(obj);
//
//        /*
//         * serialize json object to a file
//         */
//        try {
//            out = new FileOutputStream(filename);
//
//            writer = new JsonWriter( new OutputStreamWriter(out, "UTF-8") );
//            writer.setIndent("    ");
//            writer.beginArray();
//
//            gson.toJson(obj, obj.getClass(), writer);
//
//            writer.endArray();
//            writer.close();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    protected Object read(String uniqueName, Object obj) {
//        InputStream in = null;
//        JsonReader reader = null;
//        Object rehydratedObj = null;
//
//        String filename = buildFilename(obj, uniqueName);
//        JSONObject json = null;
//
//        /*
//         * deserialize json file into an object
//         */
//        try {
//            JSONArray jsonArray = (JSONArray) new JSONParser().parse( new FileReader(filename) );
//            json = (JSONObject) jsonArray.get(0);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        if (obj instanceof User) {
//            Login login     = gson.fromJson( json.get( "login"       ).toString(), Login.class   );
//            Address home    = gson.fromJson( json.get( "homeaddress" ).toString(), Address.class );
//            Address work    = gson.fromJson( json.get( "workaddress" ).toString(), Address.class );
//            Billing billing = gson.fromJson( json.get( "billing"     ).toString(), Billing.class );
//
//            User user = new User();
//            user.setLogin( login );
//            user.setHomeAddress( home );
//            user.setWorkAddress( work );
//            user.setBilling( billing );
//
//            rehydratedObj = user;
//        }
//        else {
//            throw new RuntimeException("Invalid object type. Please check your testng code.");
//        }
//
//        return rehydratedObj;
//    }
//
//    protected String readFile(String filename) {
//        String contents = "";
//
//        try{
//            InputStreamReader input = new InputStreamReader( new FileInputStream(filename) );
//            BufferedReader reader = new BufferedReader(input);
//            String line = "";
//
//            while ( (line = reader.readLine()) != null )
//                contents += line;
//            reader.close();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return contents.replaceAll("\n", "").replaceAll(" ", "");
//    }

}
