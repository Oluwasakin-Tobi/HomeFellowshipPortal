package com.portal.homeFellowship.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.utility.SSLClientFactory.HttpClientType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicUtil {
	final static DateFormat MARSHARLLERDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final static DateFormat POSTINGDATEFORMAT = new SimpleDateFormat("YYYY-MM-dd");
	final static DateFormat TRYPOSTINGDATEFORMAT = new SimpleDateFormat("YYYY/MM/dd");
	final static DateFormat MARSHARLLERDATEFORMAT2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicUtil.class);

	@Autowired
	private static HttpServletRequest request;

	@Autowired
	private static Environment environment;

	public static <T> Response marshalObj(Object obj, String logFileLocation, String requestType,
			Class<T> objectClass) {
		PrintWriter out = null;
		try {

			switch (requestType == null ? "" : requestType) {
			case "json":
				ObjectMapper mapper = new ObjectMapper();
				File file = new File(
						logFileLocation + "-" + (new SimpleDateFormat("dd-MMM-YYYY").format(new Date())) + ".json");
				out = new PrintWriter(new FileWriter(file, true));
				mapper.writeValue(out, obj);
				return new Response("00", "Success.");

			case "xml":
				JAXBContext jContext = JAXBContext.newInstance(objectClass);
				Marshaller marshallObj = jContext.createMarshaller();
				marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				File xmlFile = new File(
						logFileLocation + "-" + (new SimpleDateFormat("dd-MMM-YYYY").format(new Date())) + ".xml");
				out = new PrintWriter(new FileWriter(xmlFile, true));
				marshallObj.marshal(obj, out);
				return new Response("00", "Success.");

			case "posting":
				File postingFile = new File(
						logFileLocation + "-" + (new SimpleDateFormat("dd-MMM-YYYY").format(new Date())) + ".xml");
				out = new PrintWriter(new FileWriter(postingFile, true));
				out.println(obj);
				return new Response("00", "Success.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response("99", "********Oops Something went wrong **********" + e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return new Response();
	}

	public static String removeCustom(String roleName) {
		if (roleName == null)
			return "";
		if ((org.apache.commons.lang3.StringUtils.containsIgnoreCase(roleName, "CUSTOM_")))
			return roleName.replace("CUSTOM_", "");
		else
			return roleName;
	}

	public static Response validateEmail(String stepEmail) {
		// Response response = new Response();
		String string = stepEmail;
		if (string != null) {
			String[] split = string.split("@");
			LOGGER.info("SPlit-->" + split[0]);
			if ((split[0].length() > 1) && ((string.contains("@gmail.com")||string.contains("@yahoo.com")))) {
				// DO ACTION FOR CORRECT EMAIL
				LOGGER.info("**Yes==> " + string + " **");
				return new Response("00", "All Valid.");
			} else {// DO ACTION FOR INCORRECT EMAIL
				LOGGER.info("**NoNo==> " + string + " **");
				return new Response("96", "the email address is invalid ==> ");
			}

		} else
			return new Response();
	}

	public static String getClientIp() {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}

	public static boolean containsRole(String userProfile, Set<UserProfileAdmin> userProfiles) {
		if (userProfile == null || userProfile == null || userProfile.isEmpty())
			return false;
		for (UserProfileAdmin profile : userProfiles) {
			if (userProfile.equals(profile.getType()))
				return true;
		}
		return false;
	}

	public static boolean containsCaseInsensitiveInsensitiveStr(String searchStr, List<String> list) {
		if (searchStr == null || searchStr.isEmpty())
			return false;
		for (String string : list) {
			if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(string, searchStr))
				return true;
		}
		return false;
	}

	public static UserToRoleApp getUserRoleAttributes(String userName, List<UserToRoleApp> pendingUsertorole) {
		if (userName == null || pendingUsertorole == null || pendingUsertorole.isEmpty())
			return new UserToRoleApp();
		for (UserToRoleApp userRole : pendingUsertorole) {
			if (userName.equalsIgnoreCase(userRole.getUserName()))
				return userRole;
		}
		return new UserToRoleApp();
	}

	public static UserToRoleApp getEditUserRoleFromList(String usertoroleID, List<UserToRoleApp> pendusers) {
		if (usertoroleID == null || pendusers == null || pendusers.isEmpty())
			return new UserToRoleApp();
		for (UserToRoleApp editUser : pendusers) {
			// LOGGER.info(">>> userTill "+userTill+" <<<");
			if (usertoroleID.equalsIgnoreCase(String.valueOf(editUser.getUsertoroleID())))
				return editUser;
		}
		return new UserToRoleApp();
	}

	public static UserRemoveRole getPendingUser(String userID, List<UserRemoveRole> pendusers) {
		if (userID == null || pendusers == null || pendusers.isEmpty())
			return new UserRemoveRole();
		for (UserRemoveRole editUser : pendusers) {
			// LOGGER.info(">>> userTill "+userTill+" <<<");
			if (userID.equalsIgnoreCase(String.valueOf(editUser.getUserID())))
				return editUser;
		}
		return new UserRemoveRole();
	}

	public static EditUser getEditUserFromList(String userID, List<EditUser> pendusers) {
		if (userID == null || pendusers == null || pendusers.isEmpty())
			return new EditUser();
		for (EditUser editUser : pendusers) {
			// LOGGER.info(">>> userTill "+userTill+" <<<");
			if (userID.equalsIgnoreCase(String.valueOf(editUser.getUserID())))
				return editUser;
		}
		return new EditUser();
	}

	public static String encryptPayload(String clearPayload) {
		return EncryptDecrypt.encrypt(clearPayload);
	}

	public static String decryptPayload(String encryptedPayload) {
		return EncryptDecrypt.decrypt(encryptedPayload);
	}

	public static String splitTrim(String step) {
		if (step == null)
			return "";
		String[] arr = step.split("-");
		return arr[0].trim();
	}

	////////////////////////////// Email/////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String notifyARIBAReviewerOnRejectBody(String supplierId, String purchaseOrderNo, String reviewer,
			String authorizer, String comment, String filePath) {
		StringBuilder responseSB = new StringBuilder();

		LOGGER.info("********Show me filepath **********" + filePath);
		LOGGER.info("********Show me **********" + supplierId+purchaseOrderNo+reviewer+authorizer+comment);
		// Files.lines(Paths.get(filePath),
		// StandardCharsets.UTF_8).forEach(System.out::println);
		try {
			Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).forEach(line -> responseSB.append(line));
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("********Oops Something went wrong **********" + e);
		}
		return responseSB.toString() != null
				? responseSB.toString().replace("[supplierId]", supplierId)
						.replace("[purchaseOrderNo]", purchaseOrderNo).replace("[Reviewer]", reviewer)
						.replace("[authorizer]", authorizer).replace("[comment]", comment)
				// .replace("[appServerandPort]", appServerAndPort)
				: "";
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////


		public static List<Custactivities> getSelectedActivities(List<Custactivities> custactivitiesFinal,
			String[] activities) {
		List <Custactivities> responseObj = new ArrayList<>();
		if (custactivitiesFinal==null || custactivitiesFinal.isEmpty())
			return new ArrayList<>();
		
		if (activities==null || activities.length==0)
			return custactivitiesFinal; 
		
		for (Custactivities activity: custactivitiesFinal)
		{
			activity.setSelected(equalsCaseInsensitiveInsensitiveStr(activity.getActName(),Arrays.asList(activities)));
			responseObj.add(activity);
		} 
		
		return responseObj;
	}
		
		public static boolean equalsCaseInsensitiveInsensitiveStr(String searchStr, List<String> list){
			 if ( searchStr==null|| searchStr.isEmpty())
				 return false;
			 for (String string:list){
					//LOGGER.error("********Oops Something went wrong **********" + searchStr);
				//	LOGGER.error("********Oops Something went wrong **********" + string);
//				 if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(string,searchStr
//							)) 
					 
					 if (string.equalsIgnoreCase(searchStr))
					 return true;
			 }
				 return false;
		// return l.stream().anyMatch(x -> x.trim().equalsIgnoreCase(s));
		 }

	///////////////////////////////////////////////////////////////////////////////////////
		
		public static void searchAndReplace(List<Object> texts, Map<String, String> values){

	        // -- scan all expressions  
	        // Will later contain all the expressions used though not used at the moment
	        List<String> els = new ArrayList<String>(); 

	        StringBuilder sb = new StringBuilder();
	        int PASS = 0;
	        int PREPARE = 1;
	        int READ = 2;
	        int mode = PASS;

	        // to nullify
	        List<int[]> toNullify = new ArrayList<int[]>();
	        int[] currentNullifyProps = new int[4];

	        // Do scan of els and immediately insert value
	        for(int i = 0; i<texts.size(); i++){
	            Object text = texts.get(i);
	            Text textElement = (Text) text;
	            String newVal = "";
	            String v = textElement.getValue();
//	          System.out.println("text: "+v);
	            StringBuilder textSofar = new StringBuilder();
	            int extra = 0;
	            char[] vchars = v.toCharArray();
	            for(int col = 0; col<vchars.length; col++){
	                char c = vchars[col];
	                textSofar.append(c);
	                switch(c){
	                case '$': {
	                    mode=PREPARE;
	                    sb.append(c);
//	                  extra = 0;
	                } break;
	                case '{': {
	                    if(mode==PREPARE){
	                        sb.append(c);
	                        mode=READ;
	                        currentNullifyProps[0]=i;
	                        currentNullifyProps[1]=col+extra-1;
	                        System.out.println("extra-- "+extra);
	                    } else {
	                        if(mode==READ){
	                            // consecutive opening curl found. just read it
	                            // but supposedly throw error
	                            sb = new StringBuilder();
	                            mode=PASS;
	                        }
	                    }
	                } break;
	                case '}': {
	                    if(mode==READ){
	                        mode=PASS;
	                        sb.append(c);
	                        els.add(sb.toString());
	                        newVal +=textSofar.toString()
	                                +(null==values.get(sb.toString())?sb.toString():values.get(sb.toString()));
	                        textSofar = new StringBuilder();
	                        currentNullifyProps[2]=i;
	                        currentNullifyProps[3]=col+extra;
	                        toNullify.add(currentNullifyProps);
	                        currentNullifyProps = new int[4];
	                        extra += sb.toString().length();
	                        sb = new StringBuilder();
	                    } else if(mode==PREPARE){
	                        mode = PASS;
	                        sb = new StringBuilder();
	                    }
	                }
	                default: {
	                    if(mode==READ) sb.append(c);
	                    else if(mode==PREPARE){
	                        mode=PASS;
	                        sb = new StringBuilder();
	                    }
	                }
	                }
	            }
	            newVal +=textSofar.toString();
	            textElement.setValue(newVal);
	        }

	        // remove original expressions
	        if(toNullify.size()>0)
	        for(int i = 0; i<texts.size(); i++){
	            if(toNullify.size()==0) break;
	            currentNullifyProps = toNullify.get(0);
	            Object text = texts.get(i);
	            Text textElement = (Text) text;
	            String v = textElement.getValue();
	            StringBuilder nvalSB = new StringBuilder();
	            char[] textChars = v.toCharArray();
	            for(int j = 0; j<textChars.length; j++){
	                char c = textChars[j];
	                if(null==currentNullifyProps) {
	                    nvalSB.append(c);
	                    continue;
	                }
	                // I know 100000 is too much!!! And so what???
	                int floor = currentNullifyProps[0]*100000+currentNullifyProps[1];
	                int ceil = currentNullifyProps[2]*100000+currentNullifyProps[3];
	                int head = i*100000+j;
	                if(!(head>=floor && head<=ceil)){
	                    nvalSB.append(c);
	                } 

	                if(j>currentNullifyProps[3] && i>=currentNullifyProps[2]){
	                    toNullify.remove(0);
	                    if(toNullify.size()==0) {
	                        currentNullifyProps = null;
	                        continue;
	                    }
	                    currentNullifyProps = toNullify.get(0);
	                }
	            }
	            textElement.setValue(nvalSB.toString());
	        }
	    }

	    public static WordprocessingMLPackage getTemplate(String name)
	            throws Docx4JException, FileNotFoundException {
	        WordprocessingMLPackage template = WordprocessingMLPackage
	                .load(new FileInputStream(new File(name)));
	        return template;
	    }

	    public static List<Object> getAllElementFromObject(Object obj,
	            Class<?> toSearch) {
	        List<Object> result = new ArrayList<Object>();
	        if (obj instanceof JAXBElement)
	            obj = ((JAXBElement<?>) obj).getValue();

	        if (obj.getClass().equals(toSearch))
	            result.add(obj);
	        else if (obj instanceof ContentAccessor) {
	            List<?> children = ((ContentAccessor) obj).getContent();
	            for (Object child : children) {
	                result.addAll(getAllElementFromObject(child, toSearch));
	            }

	        }
	        return result;
	    }

	    public void replacePlaceholder(WordprocessingMLPackage template,
	            String name, String placeholder) {
	        List<Object> texts = getAllElementFromObject(
	                template.getMainDocumentPart(), Text.class);

	        for (Object text : texts) {
	            Text textElement = (Text) text;
	            if (textElement.getValue().equals(placeholder)) {
	                textElement.setValue(name);
	            }
	        }
	    }

	    public static void writeDocxToStream(WordprocessingMLPackage template,
	            String target) throws IOException, Docx4JException {
	        File f = new File(target);
	        template.save(f);
	    }
	    
}
