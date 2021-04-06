@GetMapping("/api/{jobId}/allskilltests")
@ PreAuthorize("hasRole('ADMIN')")
public Map getAllSkillTest(@PathVariable(value = "jobId") Long jobId) {
  List<Skill> skill = skillsRepository.findByJob(jobRepository.findById(jobId));
  Map<String, List> map = new HashMap<String, List>();
  List<Map<String, Object>> res = new ArrayList<>();

   for (Skill sk: skill) {
    Map < String, Object > skMap = new HashMap < > ();

    List<SkillDetail> skillDetail = skillDetailRepository.findBySkill(sk);
    Map<String, SkillDetail> sdMap = new HashMap<>();
    skMap.put("data", sk);
    res.add(skMap);

    List<Map<String, SkillDetail>> lsdt = new ArrayList<>();
    for (SkillDetail sdt: skillDetail) {
      Map<String, SkillDetail> skdMap = new HashMap<>();
      skdMap.put("data", sdt);
      lsdt.add(skdMap);
    }
    skMap.put("children", lsdt);
  }
  map.put("data", res);
  return map;
}