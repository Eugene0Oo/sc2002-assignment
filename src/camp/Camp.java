package camp;

import java.util.ArrayList;
import java.util.Scanner;
import committeeMember.CommitteeMember;
import enquiry.Enquiry;
import enums.Faculty;
import staff.Staff;
import student.Student;
import suggestion.Suggestion;
import file.Input;

/**
 * Represents a <code>camp</code>. Used in conjunction with the <code>Staff</code> class. Can only
 * be created by a <code>Staff</code>.
 * 
 * @author Nah Wei Jie
 * @version 1.04
 * @see <code>Staff</code>
 */
public class Camp {
  /**
   * Represents the count of camps created so far.
   */
  private static int idCount;
  /**
   * Represents the ID of this camp.
   */
  private String campID;
  /**
   * Represents if this camp is currently visible.
   */
  private boolean isVisible = false;
  /**
   * Represents the details of this camp.
   * 
   * @see <code>Detail</code>
   */
  private Detail details;
  /**
   * Represents the participants of this camp.
   */
  private ArrayList<Student> participants = new ArrayList<>();
  /**
   * Represents the participants who has withdrawn after registering for this camp.
   */
  private ArrayList<Student> previouslyWithdrawn = new ArrayList<>();
  /**
   * Represents the committee members of this camp.
   */
  private ArrayList<CommitteeMember> committee = new ArrayList<>();
  /**
   * Represents the enquiries submitted for this camp.
   */
  private ArrayList<Enquiry> enquiries = new ArrayList<>();
  /**
   * Represents the suggestions submitted for this camp.
   */
  private ArrayList<Suggestion> suggestions = new ArrayList<>();
  
  public String getCampID() {
    return campID;
  }

  public boolean getisVisible() {
    return isVisible;
  }

  public Detail getDetails() {
    return details;
  }

  public ArrayList<Student> getParticipants() {
    return participants;
  }

  public ArrayList<Student> getPreviouslyWithdrawn() {
    return previouslyWithdrawn;
  }

  public ArrayList<CommitteeMember> getCommittee() {
    return committee;
  }

  public ArrayList<Enquiry> getEnquiries() {
    return enquiries;
  }

  public ArrayList<Suggestion> getSuggestions() {
    return suggestions;
  }


  // non-default methods, to be reflected in UML

  /**
   * Custom constructor method. Uses multiple set methods from this camp's <code>detail</code>
   * object, various get input methods from the <code>Input</code> class are injected to provide
   * user defined arguments. Only called by a <code>Staff</code> object.
   * 
   * @see <code>Staff</code>, <code>Detail</code>
   * @param sc Scanner object to be injected
   * @param aStaff Staff object to be injected
   */
  public Camp(Scanner sc, Staff aStaff) {
    idCount++;
    this.campID = aStaff.getUserID() + idCount;
    this.details = new Detail(sc, aStaff);
  }

  /**
   * Sets this camp to be visible.
   */
  public void setVisibleOn() {
    this.isVisible = true;
  }

  /**
   * Sets the camp to not be visible. Only allowed when the <code>hasNoParticipants</code> and
   * <code>hasNoCommittee</code> methods returns true.
   */
  public void setVisibleOff() {
    if (this.hasNoCommittee() && this.hasNoParticipants()) {
      System.out.println(
          "You cannot set this camp to not be visible once student(s) or committee member(s) have registered for it.");
    } else {
      this.isVisible = false;
    }
  }

  /**
   * Custom settor method. Uses multiple set methods from this camp's <code>detail</code> object,
   * various get input methods from the <code>Input</code> class are injected to provide user
   * defined arguments. Only called by a <code>Staff</code> object.
   * 
   * @see <code>Staff</code>, <code>Detail</code>
   * @param sc Scanner object to be injected
   */
  public void setDetails(Scanner sc) {
    this.details.setName(Input.getStringInput("Enter the name of the camp: ", sc));
    this.details.setLocation(Input.getStringInput("Enter the location of the camp: ", sc));
    this.details
        .setDescription(Input.getStringInput("Enter the description of the camp: ", sc));
    this.details.setFaculty(Faculty.getFacultyFromStringInput(sc));
    this.details.setStartDate(Input.getDateFromIntInputs("starting date", sc));
    this.details.setEndDate(Input.getDateFromIntInputs("ending date", sc));
    this.details
        .setRegistrationClosingDate(Input.getDateFromIntInputs("registration deadline", sc));
    this.details
        .setTotalSlots(Input.getIntInput("Enter the total slots for this camp: ", sc));
  }

  /**
   * Adds a student into this camp's <code>participants</code> list.
   * 
   * @param aStudent <code>Student</code> object
   */
  public void addParticipant(Student aStudent) {
    this.participants.add(aStudent);
  }

  /**
   * Return true if there are no participants in this camp.
   */
  public boolean hasNoParticipants() {
    return this.participants.isEmpty();
  }

  /**
   * Return true if there are no committee in this camp.
   */
  public boolean hasNoCommittee() {
    return this.committee.isEmpty();
  }

  /**
   * Adds a student into this camp's <code>previouslyWithdrawn</code> list.
   * 
   * @param aStudent <code>Student</code> object
   */
  public void addWithdrawn(Student aStudent) {
    this.previouslyWithdrawn.add(aStudent);
  }

  /**
   * Removes a student from this camp's <code>participants</code> list.
   * 
   * @param aStudent <code>Student</code> object
   */
  public void removeParticipant(Student aStudent) {
    this.participants.remove(aStudent);

  }

  /**
   * Adds a committee membber into this camp's <code>committee</code> list.
   * 
   * @param aCommitteeMember <code>CommitteeMember</code> object
   */
  public void addCommittee(CommitteeMember aCommitteeMember) {
    this.committee.add(aCommitteeMember);
  }

  /**
   * Calculates and returns the remaining number of slots for this camp.
   * 
   * @return integer result of this camp details' number of total slots subtracted by the size of
   *         this camp's participants list and the size of the committee array.
   */
  public int calculateRemainingSlots() {
    return this.details.getTotalSlots() - this.participants.size() - this.committee.size();
  }

  /**
   * Calculates and returns the remaining number of slots for committee members for this camp.
   * 
   * @return integer result of this camp details' number of committee slots subtracted by the size
   *         of this camp's committee list.
   */
  public int calculateRemainingCommitteeSlots() {
    return this.details.getCommitteeSlots() - this.committee.size();
  }

  public void addEnquiry(Enquiry enquiry)
  {
	  if (this.enquiries.contains(enquiry))
		  System.out.println("Enquiry already present in this camp. Please double check and try again");
	  else
		  this.enquiries.add(enquiry);
  }
  
  public void removeEnquiry(Enquiry enquiry)
  {
	  if (this.enquiries.contains(enquiry))
		  this.enquiries.remove(enquiry);
	  else
		  System.out.println("Enquiry is not found. Please double check and try again.");
  }
  
  public void addSuggestions(Suggestion suggestion)
  {
	  if (this.suggestions.contains(suggestion))
		  System.out.println("Suggestion already present in this camp. Please double check and try again");
	  else
		  this.suggestions.add(suggestion);
  }
  
  public void removeSuggestion(Suggestion suggestion)
  {
	  if (this.suggestions.contains(suggestion))
		  this.suggestions.remove(suggestion);
	  else
		  System.out.println("Suggestion is not found. Please double check and try again.");
  }
  
  public String toString() {
    String delimiter = " | ";
    return this.campID + delimiter + this.isVisible + delimiter + this.details.toString();
  }

  public static String generateCSVHeaders() {
    String delimiter = ", ";
    return "campID" + delimiter + "isVisible" + delimiter + Detail.generateCSVHeaders();
  }

  public String toCSV() {
    generateCSVHeaders();
    String delimiter = ", ";
    return this.campID + delimiter + this.isVisible + delimiter + this.details.toCSV();
  }

  public void print() {
    String delimiter = "-";
    String paddingParameters =
        "| %-10s | %-20s | %-20s | %-20s | %-40s | %-10s | %-25s | %-25s | %-25s | %-15s | \n";
    System.out.println(delimiter.repeat(240));
    System.out.printf(paddingParameters, "CampID", "StaffInChargeName", "Name", "Location",
        "Description", "Faculty", "Registration Closing Date", "Start Date", "Start Date",
        "Remaining Slots");
    System.out.println(delimiter.repeat(240));
    System.out.printf(paddingParameters, this.campID, this.details.getStaffInChargeName(),
        this.details.getName(), this.details.getLocation(), this.details.getDescription(),
        this.details.getFaculty(),
        this.details.getRegistrationClosingDate().getDayOfMonth() + "-"
            + this.details.getRegistrationClosingDate().getMonthValue() + "-"
            + this.details.getRegistrationClosingDate().getYear(),
        this.details.getStartDate().getDayOfMonth() + "-"
            + this.details.getStartDate().getMonthValue() + "-"
            + this.details.getStartDate().getYear(),
        this.details.getEndDate().getDayOfMonth() + "-" + this.details.getEndDate().getMonthValue()
            + "-" + this.details.getEndDate().getYear(),
        this.details.getTotalSlots());
    System.out.println(delimiter.repeat(240));
    System.out.println();
  }

  public void detailedPrint() {
    String delimiter = "-";
    String paddingParameters = "| %-10s | %-10s | %-15s | %-30s | %-10s | \n";
    this.print();
    System.out.println("Participants");
    System.out.println(delimiter.repeat(91));
    System.out.printf(paddingParameters, "UserID", "Role", "Name", "Email", "Faculty");
    System.out.println(delimiter.repeat(91));
    for (Student stu : this.getParticipants()) {
      stu.toString();
    }
    System.out.println(delimiter.repeat(91));
    System.out.println();
    System.out.println("Committee");
    System.out.printf(paddingParameters, "UserID", "Role", "Name", "Email", "Faculty");
    for (CommitteeMember cm : this.getCommittee()) {
      cm.toString();
    }
    System.out.println(delimiter.repeat(91));
    System.out.println();
  }
}
