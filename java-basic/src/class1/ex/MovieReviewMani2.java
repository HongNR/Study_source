package class1.ex;

public class MovieReviewMani2 {

    public static void main(String[] args) {
        MovieReview[] reviews = new MovieReview[2];

        MovieReview inception = new MovieReview();
        inception.title = "인셉션";
        inception.review = "인생은 무한 루프";
        reviews[0] = inception;

        MovieReview aboutTiem = new MovieReview();
        aboutTiem.title = "어바웃 타임";
        aboutTiem.review = "인생 시간 영화";
        reviews[1] = aboutTiem;

        for (MovieReview review : reviews) {
            System.out.println("영화 제목: " + review.title + ", 리뷰: " + review.review);
        }
    }
}
