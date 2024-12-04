package Test;

class CreditBasedStudent extends Student {
    private int creditLimit = 20; //số tín tối đa
    private int currentCredits = 0; // số tín đăng kí hiện tại

    public CreditBasedStudent(String id, String name) {
        super(id, name);
        setType("Credit-Based");
    }    
    // kiểm tra điều kiện tiên quyết
    public boolean checkPrerequisites(Course course) {
        // Lấy mảng các môn tiên quyết của khóa học
        String[] prerequisites = course.getPrerequisites();

        // Kiểm tra mỗi môn trong prerequisites
        for (String prereq : prerequisites) {
            // Duyệt qua tất cả các khóa học đã đăng ký
            boolean found = false;
            for (Course registeredCourse : getRegisteredCourses()) {
                if (registeredCourse.getName().equals(prereq)) {
                    found = true;  // Môn tiên quyết đã có trong danh sách đã đăng ký
                    break;
                }
            }
            // Nếu tìm thấy môn tiên quyết nào chưa được đăng ký, trả về false
            if (!found) {
                return false;  // Không có môn tiên quyết trong danh sách đã đăng ký
            }
        }
        return true;
    }
    
    public void registerCourse(Course course) {
        if (!checkPrerequisites(course)) {    // Kiểm tra điều kiện tiên quyết trước
            System.out.println(getName() + " không thể đăng kí môn " + course.getName() + " do chưa đạt điều kiện tiên quyết!");
            return;
        }
        if (currentCredits + course.getCredit() > creditLimit) {    // Kiểm tra giới hạn tín chỉ
            System.out.println(getName() + " không thể đăng kí môn " + course.getName() + " vì đã đạt giới hạn số tín chỉ đăng ký!");
            return;
        }
        if (getRegisteredCourses().contains(course)) {   //Kiểm tra khóa học trùng lặp
            System.out.println("Khóa học " + course.getName() + " đã được đăng ký trước đó.");
            return;
        }
        getRegisteredCourses().add(course);   // Đăng ký môn học
        currentCredits += course.getCredit();
        System.out.println(getName() + " đã đăng kí môn: " + course.getName());
    }
}

class PartTimeStudent extends Student {		
    public PartTimeStudent(String id, String name) {
        super(id, name);
        setType("Part-Time");
    }
    
    public void registerCourse(Course course) {  //Ví dụ về phương thức RegisterCourse() cho PartTimeStudent
    	getRegisteredCourses().add(course);
    }
}
