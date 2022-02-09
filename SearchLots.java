// --== CS400 File Header Information ==--
// Name: Owen Graham
// Email: ohgraham@wisc.edu
// Team: KA
// Role: Data Wrangler 2
// TA: Siddarth Mohan
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchLots {

    /**
     * Metadata holder for ParkingLot. Counts pattern matches as it is
     * instantiated.
     */
    private static class SearchLot {
        public ParkingLot lot;
        public int hits;
        public SearchLot(ParkingLot lot, List<Pattern> patterns) {
            this.lot = lot;
            this.hits = 0;
            String[] fields = new String[] {lot.getAddress().toLowerCase(),
                                            String.valueOf(lot.getLotNum())};
            for (Pattern pattern : patterns) {
                for (String field : fields) {
                    if (pattern.matcher(field).find()) {
                        this.hits++;
                    }
                }
            }
        }
    }

    /**
     * Perform a search on a list of ParkingLot objects.
     *
     * @param lots Lots to seearch through
     * @param search Plain search string
     * @return List of matches, sorted by descending matchiness.
     */
    public static List<ParkingLot> sort(List<ParkingLot> lots, String search) {
        // Normalize and escape `search` into a suitable regex pattern.
        search = search.toLowerCase()
                       .replaceAll("([\\Q\\$^*()+{}[]|.?\\E])", "\\\\$1")
                       .replaceAll("[ ]{2,}", " ");
        // Match not only the entire search string, but each word in it.
        List<Pattern> patterns = new LinkedList<>();
        patterns.add(Pattern.compile(search));
        for (String searchPart : search.split(" ")) {
            patterns.add(Pattern.compile(searchPart));
        }
        List<ParkingLot> sorted =
            lots.stream()
                // Convert to SearchLot, counting search matches on each
                // item.
                .map((lot) -> new SearchLot(lot, patterns))
                // Filter out non-matches.
                .filter((sLot) -> sLot.hits > 0)
                // Sort by descending hit counts.
                .sorted((sLot, otherSLot) -> otherSLot.hits - sLot.hits)
                // Convert back to ParkingLot.
                .map((sLot) -> sLot.lot)
                .collect(Collectors.toList());
        return sorted;
    }

}